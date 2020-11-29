package sexy.tea.service;

import sexy.tea.model.ErrorLog;
import sexy.tea.model.common.Result;

/**
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-11-28 下午 02:42
 * desc:
 */
public interface ErrorLogService {

    Result find(int pageNum, int pageSize);

    void insertLog(ErrorLog build);
}
