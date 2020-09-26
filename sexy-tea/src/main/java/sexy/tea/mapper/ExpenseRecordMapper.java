package sexy.tea.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sexy.tea.model.ExpenseRecord;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Mapper
public interface ExpenseRecordMapper extends tk.mybatis.mapper.common.Mapper<ExpenseRecord> {
    int updateBatch(List<ExpenseRecord> list);

    int updateBatchSelective(List<ExpenseRecord> list);

    int batchInsert(@Param("list") List<ExpenseRecord> list);

    int insertOrUpdate(ExpenseRecord record);

    int insertOrUpdateSelective(ExpenseRecord record);
}