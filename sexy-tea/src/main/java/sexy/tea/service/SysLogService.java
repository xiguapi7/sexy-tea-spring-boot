package sexy.tea.service;

import sexy.tea.model.SysLog;
import sexy.tea.model.common.Result;

/**
 * 系统日志接口
 * <p>
 * author 大大大西西瓜皮🍉
 * date 18:00 2020-10-13
 * description:
 */
public interface SysLogService {

    Result find(int pageNum, int pageSize);

    void insertLog(SysLog sysLog);
}

