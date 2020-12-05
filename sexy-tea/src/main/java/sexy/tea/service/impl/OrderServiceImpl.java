package sexy.tea.service.impl;

import cn.hutool.core.lang.UUID;
import com.alipay.easysdk.factory.Factory.Payment;
import com.alipay.easysdk.payment.common.models.AlipayTradeQueryResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import sexy.tea.exception.BusinessException;
import sexy.tea.mapper.OrderGoodsMapper;
import sexy.tea.mapper.OrderMapper;
import sexy.tea.mapper.OrderShippingMapper;
import sexy.tea.model.Order;
import sexy.tea.model.OrderGoods;
import sexy.tea.model.ShoppingRecord;
import sexy.tea.model.User;
import sexy.tea.model.common.Pager;
import sexy.tea.model.common.Result;
import sexy.tea.model.dto.OrderDto;
import sexy.tea.service.OrderService;
import sexy.tea.service.ShoppingRecordService;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 订单服务接口实现类
 * <p>
 * TODO 订单支付分布式锁等功能的实现
 * <p>
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    /**
     * Jackson序列化和反序列化的MAPPER对象
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final OrderMapper orderMapper;

    private final OrderShippingMapper orderShippingMapper;

    private final OrderGoodsMapper orderGoodsMapper;

    private final ShoppingRecordService shoppingRecordService;

    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper,
                            OrderShippingMapper orderShippingMapper,
                            OrderGoodsMapper orderGoodsMapper,
                            ShoppingRecordService shoppingRecordService) {
        this.orderMapper = orderMapper;
        this.orderShippingMapper = orderShippingMapper;
        this.orderGoodsMapper = orderGoodsMapper;
        this.shoppingRecordService = shoppingRecordService;
    }

    /**
     * 分页查询订单
     *
     * @param pageNum  当前页
     * @param pageSize 条数
     *
     * @return 结果集
     */
    @Cacheable(value = "order_items")
    @Override
    public Result find(int pageNum, int pageSize) {

        final Page<Order> page = PageHelper.startPage(pageNum, pageSize);
        final Example example = Example.builder(Order.class).build();
        example.createCriteria().andNotEqualTo("status", -1);
        example.setOrderByClause("update_time DESC");
        orderMapper.selectByExample(example);

        return Result.success("订单查询成功", Pager.<Order>builder()
                .pageNum(pageNum)
                .pageSize(pageSize)
                .total(page.getTotal())
                .result(page.getResult())
                .build());
    }

    /**
     * 生成订单
     *
     * @param orderDto 订单参数
     *
     * @return 封装订单号的响应
     */
    @CachePut(value = {"order_items", "order_uid_items", "order_id_goods"})
    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public Result createOrder(OrderDto orderDto) {
        if (orderDto == null || orderDto.getUser() == null || orderDto.getRecord() == null) {
            return Result.business("参数错误", Optional.empty());
        }

        // 获取参数
        final User user = orderDto.getUser();
        ShoppingRecord record = orderDto.getRecord();

        log.info("uid = {}, record = {}", user, record);

        // 生成订单号
        String orderId = UUID.fastUUID().toString().replace("-", "");
        log.info("orderId = {}", orderId);

        // 创建订单对象
        Order order = Order.builder()
                .id(orderId)
                .uid(user.getId())
                .username(user.getUsername())
                .phone(user.getPhone())
                .total(record.getTotal())
                .isPay(0)
                .nickname(user.getNickname())
                .createTime(new Date())
                .build();

        log.info("order = {}", order);

        // 插入订单对象
        orderMapper.insertSelective(order);

        // 生成订单商品项
        OrderGoods orderGoods = OrderGoods.builder()
                .orderId(orderId)
                .items(record.getItems())
                .build();
        orderGoodsMapper.insertSelective(orderGoods);

        // 删除购物车项
        shoppingRecordService.updateShoppingRecordByUid(user.getId());

        return Result.success("生成订单成功", orderId);
    }

    /**
     * 根据订单号查询
     *
     * @param orderId 订单号
     *
     * @return 结果集
     */
    @Cacheable(value = "order_id_item")
    @Override
    public Order findByOrderId(String orderId) {
        log.info("根据订单ID查询订单, orderId = {}", orderId);

        final Example example = Example.builder(Order.class).build();
        example.createCriteria()
                .andEqualTo("id", orderId)
                .andEqualTo("status", 1);
        example.setOrderByClause("update_time DESC");

        return orderMapper.selectOneByExample(example);
    }

    /**
     * 根据订单号查询订单详情
     *
     * @param orderId 订单号
     *
     * @return 结果集
     */
    @Cacheable(value = "order_id_goods")
    @Override
    public Result findOrderGoodsByOrderId(String orderId) {
        if (StringUtils.isEmpty(orderId)) {
            return Result.business("参数错误", Optional.empty());
        }

        final Example example = Example.builder(OrderGoods.class).build();
        example.createCriteria().andEqualTo("orderId", orderId).andEqualTo("status", 1);
        example.setOrderByClause("update_time DESC");

        final OrderGoods orderGoods = orderGoodsMapper.selectOneByExample(example);
        return Result.success("查询订单项成功", orderGoods);
    }

    /**
     * 根据订单号删除订单
     *
     * @param orderId 订单号
     *
     * @return 删除结果
     */
    @CacheEvict(value = {"order_id_goods", "order_items", "order_uid_items"})
    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public Result deleteByOrderId(String orderId) {
        if (StringUtils.isEmpty(orderId)) {
            return Result.business("删除订单参数错误", Optional.empty());
        }
        log.info("删除订单, orderId = {}", orderId);
        // 根据订单号删除订单和订单项(逻辑删除)
        orderMapper.deleteByOrderId(orderId);
        orderGoodsMapper.deleteByOrderId(orderId);
        return Result.success("删除订单成功", orderId);
    }

    /**
     * 回调修改订单状态
     *
     * @param orderId 订单号
     */
    @CachePut(value = {"order_id_goods", "order_items", "order_uid_items"})
    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public void callbackUpdateOrder(String orderId) {

        final AlipayTradeQueryResponse response;
        try {
            response = Payment.Common().query(orderId);
            orderMapper.fallbackUpdateOrder(orderId);
            log.info("alipay response: {}", response);
        } catch (Exception e) {
            log.error("支付回调出错, error = {}", e.getMessage());
        }
    }

    /**
     * 用户下的订单
     *
     * @param uid 用户ID
     *
     * @return 结果集
     */
    @Cacheable(value = "order_uid_items")
    @Override
    public Result findByUid(Long uid) {
        if (uid == null || uid <= 0) {
            return Result.business("参数错误", Optional.empty());
        }
        final Example example = Example.builder(Order.class).build();
        example.createCriteria().andEqualTo("uid", uid).andNotEqualTo("status", -1);
        example.setOrderByClause("update_time DESC");
        final List<Order> orderList = orderMapper.selectByExample(example);
        return Result.success("查询订单成功", orderList);
    }
}

