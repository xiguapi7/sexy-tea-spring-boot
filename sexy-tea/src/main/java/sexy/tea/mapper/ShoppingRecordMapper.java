package sexy.tea.mapper;

import org.apache.ibatis.annotations.Mapper;
import sexy.tea.model.ShoppingRecord;

/**
 * @author 大大大西西瓜皮🍉
 * @summary
 * @since 2020-11-4 下午 03:07
 */
@Mapper
public interface ShoppingRecordMapper extends tk.mybatis.mapper.common.Mapper<ShoppingRecord> {
    void deleteByUid(Long uid);

    void updateShoppingRecordByUid(Long uid);
}