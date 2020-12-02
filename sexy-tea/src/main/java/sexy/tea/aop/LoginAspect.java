package sexy.tea.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sexy.tea.model.LoginLog;
import sexy.tea.model.common.Result;
import sexy.tea.model.vo.UserVO;
import sexy.tea.service.LoginLogService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * 登录日志切面
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-11-28 上午 12:57
 * desc:
 */
@Aspect
@Component
@Slf4j
public class LoginAspect {

    private final LoginLogService loginLogService;

    @Autowired
    public LoginAspect(LoginLogService loginLogService) {
        this.loginLogService = loginLogService;
    }

    @Pointcut("execution(* sexy.tea.controller.UserController.login(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        final RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        final HttpServletRequest request = sra.getRequest();

        final String method = request.getMethod();
        final String uri = request.getRequestURI();
        log.info("----------------------------------------------------------");
        log.info("请求开始, URI: {}, method: {}", uri, method);
        final long start = System.currentTimeMillis();

        // result的值就是被拦截方法的返回值
        Result result = (Result) pjp.proceed();

        final long use = System.currentTimeMillis() - start;
        log.info("请求结束, URI: {}, 耗时={}", uri, use);
        log.info("----------------------------------------------------------");

        // 系统日志记录
        String ip;
        if (request.getHeader("x-forwarded-for") == null) {
            ip = request.getRemoteAddr();
            log.info("ip: {}", ip);
        } else {
            ip = request.getHeader("x-forwarded-for");
            log.info("x-forwarded-for ip: {}", ip);
        }

        final UserVO userVO = (UserVO) result.getData();
        loginLogService.insertLog(LoginLog.builder()
                .ip(ip)
                .uid(userVO.getId())
                .username(userVO.getUsername())
                .loginSessionId(userVO.getLoginSessionId())
                .role(userVO.getRole())
                .createTime(LocalDateTime.now())
                .build());

        return result;
    }
}
