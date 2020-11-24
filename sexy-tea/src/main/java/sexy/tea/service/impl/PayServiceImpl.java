package sexy.tea.service.impl;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.factory.Factory.Payment;
import com.alipay.easysdk.payment.common.models.AlipayTradeQueryResponse;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import sexy.tea.exception.BusinessException;
import sexy.tea.mapper.OrderMapper;
import sexy.tea.model.Order;
import sexy.tea.model.bo.PaymentBO;
import sexy.tea.model.common.Result;
import sexy.tea.service.OrderService;
import sexy.tea.service.PayService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 支付接口实现类
 *
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-11-23 上午 12:16
 * desc:
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    private final OrderService orderService;

    private final OrderMapper orderMapper;

    @Value("${alipay.returnUrl}")
    private String returnUrl;

    @Autowired
    public PayServiceImpl(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result confirm(PaymentBO paymentBO) {
        // 参数校验
        if (paymentBO == null || StringUtils.isEmpty(paymentBO.getOrderId())) {
            return Result.business("参数错误", Optional.empty());
        }
        log.info("发起支付, 参数: {}", paymentBO);

        final Order order = orderService.findByOrderId(paymentBO.getOrderId());
        log.info("查询到的Order对象, order = {}", order);

        if (order == null) {
            return Result.business("订单参数错误", Optional.empty());
        }

        // 调用sdk，发起支付
        AlipayTradePagePayResponse response = null;
        try {
            response = Factory.Payment
                    // 选择网页支付平台
                    .Page()
                    // 调用支付方法，设置订单名称、我们自己系统中的订单号、金额、回调页面
                    .pay(paymentBO.getSubject(), order.getId(), order.getTotal().toString(), returnUrl);
            // 这里的response.body，就是一个可以直接加载的html片段, 前端从data中取出html片段.
            final String body = response.getBody();
            log.info("body: {}", body);

            return Result.success("发起支付成功!", body);
        } catch (Exception e) {
            log.error("发起支付出错, 错误信息: {}", e.getMessage());
            return Result.business("发起支付出错, 请重试!", "");
        }
    }

    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public Result fallback(HttpServletRequest request) {
        try {
            log.info("--------------支付宝支付回调-------------");

            // params用于接收回调请求参数, 作为验签参数Map
            Map<String, String> params = new HashMap<>(16);
            final Map<String, String[]> parameterMap = request.getParameterMap();

            // 转化回调请求的参数
            parameterMap.forEach((k, v) -> params.putIfAbsent(k, parameterMap.get(k)[0]));

            // 验签参数日志记录
            log.info("------------------------验签参数------------------------");
            params.forEach((k, v) -> log.info("{} - {}", k, v));
            log.info("------------------------验签参数------------------------");

            final String outTradeNo = params.get("out_trade_no");
            final AlipayTradeQueryResponse response = Payment.Common().query(outTradeNo);
            log.info("AlipayTradeQueryResponse = {}", response);

            if (response == null) {
                return Result.business("验签失败---", Optional.empty());
            }


            // 验签
            final Boolean verifyNotify = Factory.Payment.Common().verifyNotify(params);

            if (verifyNotify == null || !verifyNotify) {
                return Result.business("验签失败", Optional.empty());
            }

            final String tradeStatus = response.tradeStatus;
            log.info("订单状态: {}", tradeStatus);

            if ("WAIT_BUYER_PAY".equals(tradeStatus)) {
                return Result.business("交易创建，等待买家付款", "WAIT_BUYER_PAY");
            } else if ("TRADE_CLOSED".equals(tradeStatus)) {
                return Result.business("未付款交易超时关闭，或支付完成后全额退款", "TRADE_CLOSED");
            } else if ("TRADE_FINISHED".equals(tradeStatus)) {
                return Result.business("交易结束，不可退款", "TRADE_FINISHED");
            } else {
                log.info("交易支付成功, {}", tradeStatus);
            }

            final String orderId = params.get("out_trade_no");
            if (StringUtils.isEmpty(orderId)) {
                return Result.business("验签失败", Optional.empty());
            }

            final Order order = orderService.findByOrderId(orderId);
            if (order == null) {
                return Result.business("验签失败", Optional.empty());
            }

            // 修改订单状态
            order.setPayTime(new Date());
            order.setRemark("订单已支付");
            order.setIsPay(1);

            // 在数据库中作更改
            orderMapper.updateByPrimaryKey(order);

            log.info("--------------支付宝支付回调结束-------------");
            return Result.success("异步回调成功", orderId);
        } catch (Exception e) {
            log.error("异步回调验签失败, {}", e.getMessage());
            return Result.business("异步回调失败", Optional.empty());
        }
    }
}
