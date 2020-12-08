package sexy.tea.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import sexy.tea.exception.BusinessException;
import sexy.tea.mapper.SelectionMapper;
import sexy.tea.model.Selection;
import sexy.tea.model.common.Pager;
import sexy.tea.model.common.Result;
import sexy.tea.service.SelectionService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 精选服务接口实现类
 * <p>
 * TODO 精选、食品、饮品数据表重新设计, 改造为`product + category`的形式
 *
 * <p>
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Service
@Slf4j
public class SelectionServiceImpl implements SelectionService {

    private final SelectionMapper selectionMapper;

    @Autowired
    public SelectionServiceImpl(SelectionMapper selectionMapper) {
        this.selectionMapper = selectionMapper;
    }

    /**
     * 分页查询
     *
     * @param pageNum  当前页
     * @param pageSize 条数
     *
     * @return 结果集
     */
    // @Cacheable(value = "selection_items")
    @Override
    public Result find(int pageNum, int pageSize) {
        Page<Selection> page = PageHelper.startPage(pageNum, pageSize);
        Example example = Example.builder(Selection.class).build();
        example.createCriteria().andNotEqualTo("status", -1);
        List<Selection> selectionList = selectionMapper.selectByExample(example);
        return Result.success("列表查询", Pager.<Selection>builder()
                .pageNum(page.getPageNum())
                .pageSize(page.getPageSize())
                .total(page.getTotal())
                .result(selectionList)
                .build());
    }

    /**
     * 主键查询
     *
     * @param id 主键
     *
     * @return 结果集
     */
    // @Cacheable(value = "selection_id_item")
    @Override
    public Result findById(Long id) {
        Selection selection = selectionMapper.selectByPrimaryKey(id);
        if (selection == null || id <= 0) {
            return Result.notFound();
        }
        return Result.success("主键: " + id, selection);
    }

    /**
     * 保存或更改
     *
     * @param selection 数据
     *
     * @return 响应
     */
    // @CachePut(value = {"selection_items", "selection_id_item", "selection_menu_items", "selection_name_items"})
    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public Result saveOrUpdate(Selection selection) {
        if (selection == null) {
            // 异常
            return Result.business("参数异常!", Optional.empty());
        }
        if (selection.getId() == null || selection.getId() <= 0) {
            // 插入数据
            selection.setProductId(UUID.randomUUID().toString().replace("-", ""));
            selectionMapper.insertSelective(selection);
        } else {
            // 更新数据
            selectionMapper.updateByPrimaryKeySelective(selection);
        }
        return Result.success("更改成功", selection);
    }

    /**
     * 删除
     *
     * @param id 主键
     *
     * @return 响应
     */
    // @CacheEvict(value = {"selection_items", "selection_id_item", "selection_menu_items", "selection_name_items"})
    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public Result delete(Long id) {
        if (id == null || id <= 0) {
            // 校验
            return Result.business("参数错误", Optional.empty());
        }
        selectionMapper.deleteByPrimaryKey(id);
        return Result.success("删除成功", Optional.empty());
    }

    /**
     * 名称模糊分页查询
     *
     * @param name     名称
     * @param pageNum  当前页
     * @param pageSize 条数
     *
     * @return 结果集
     */
    // @Cacheable(value = "selection_name_items")
    @Override
    public Result findByName(String name, int pageNum, int pageSize) {
        if (StringUtils.isEmpty(name)) {
            return Result.business("参数错误", Optional.empty());
        }
        name = "%" + name + "%";
        Page<Selection> page = PageHelper.startPage(pageNum, pageSize);
        List<Selection> selections = selectionMapper.findByName(name);
        if (selections == null) {
            return Result.business("查询的饮品不存在", Optional.empty());
        }
        return Result.success("关键词: " + name, Pager.<Selection>builder()
                .pageSize(page.getPageSize())
                .pageNum(page.getPageNum())
                .total(page.getTotal())
                .result(selections)
                .build());
    }

    /**
     * 菜单项分页查询
     *
     * @param pageNum  当前页
     * @param pageSize 条数
     *
     * @return 结果集
     */
    // @Cacheable(value = "selection_menu_items")
    @Override
    public Result itemsMenu(int pageNum, int pageSize) {
        Page<Selection> page = PageHelper.startPage(pageNum, pageSize);
        Example example = Example.builder(Selection.class).build();
        example.createCriteria().andEqualTo("status", 1);
        List<Selection> selectionList = selectionMapper.selectByExample(example);
        return Result.success("列表查询", Pager.<Selection>builder()
                .pageNum(page.getPageNum())
                .pageSize(page.getPageSize())
                .total(page.getTotal())
                .result(selectionList)
                .build());
    }
}
