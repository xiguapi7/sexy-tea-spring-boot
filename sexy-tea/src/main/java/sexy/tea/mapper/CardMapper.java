package sexy.tea.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sexy.tea.model.Card;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Mapper
public interface CardMapper extends tk.mybatis.mapper.common.Mapper<Card> {
    int updateBatch(List<Card> list);

    int updateBatchSelective(List<Card> list);

    int batchInsert(@Param("list") List<Card> list);

    int insertOrUpdate(Card record);

    int insertOrUpdateSelective(Card record);
}