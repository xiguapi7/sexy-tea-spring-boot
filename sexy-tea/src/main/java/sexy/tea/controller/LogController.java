package sexy.tea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sexy.tea.model.common.Result;
import sexy.tea.service.ErrorLogService;
import sexy.tea.service.LoginLogService;
import sexy.tea.service.SysLogService;

/**
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-11-28 下午 02:54
 * desc:
 */
@RestController
@RequestMapping("/log")
public class LogController {

    private final SysLogService sysLogService;

    private final LoginLogService loginLogService;

    private final ErrorLogService errorLogService;

    @Autowired
    public LogController(SysLogService sysLogService, LoginLogService loginLogService, ErrorLogService errorLogService) {
        this.sysLogService = sysLogService;
        this.loginLogService = loginLogService;
        this.errorLogService = errorLogService;
    }

    @GetMapping("/sys/find/{pageNum}/{pageSize}")
    public Result sysFind(@PathVariable int pageNum, @PathVariable int pageSize) {
        return sysLogService.find(pageNum, pageSize);
    }

    @GetMapping("/error/find/{pageNum}/{pageSize}")
    public Result errorFind(@PathVariable int pageNum, @PathVariable int pageSize) {
        return errorLogService.find(pageNum, pageSize);
    }

    @GetMapping("/login/find/{pageNum}/{pageSize}")
    public Result loginFind(@PathVariable int pageNum, @PathVariable int pageSize) {
        return loginLogService.find(pageNum, pageSize);
    }
}
