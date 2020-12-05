package sexy.tea.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sexy.tea.model.ErrorLog;
import sexy.tea.model.SysLog;
import sexy.tea.model.common.Result;
import sexy.tea.service.ErrorLogService;
import sexy.tea.service.SysLogService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 日志切面
 *
 * @author 大大大西西瓜皮🍉
 * @date 20:01 2020-10-21
 * description:
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    private final SysLogService sysLogService;

    private final ErrorLogService errorLogService;

    @Autowired
    public LogAspect(SysLogService sysLogService, ErrorLogService errorLogService) {
        this.sysLogService = sysLogService;
        this.errorLogService = errorLogService;
    }

    /**
     * 定义切点Pointcut
     */
    @Pointcut("execution(* sexy.tea.controller.*Controller.*(..))")
    public void pointCut() {
    }

    /**
     * 环绕通知
     *
     * @param pjp 切点接口
     *
     * @return 方法执行后的返回值
     *
     * @throws Throwable 切面异常
     */
    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        final RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        assert sra != null;
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

        StringBuilder argsBuilder = new StringBuilder();
        final Stream<Object> stream = Arrays.stream(pjp.getArgs());
        stream.forEach(o -> argsBuilder.append(o.toString()).append(','));

        sysLogService.insertLog(SysLog.builder()
                .uri(uri)
                .use(use)
                // .result(result.toString())
                // .ip(ip)
                .args(argsBuilder.toString())
                .method(method)
                .createTime(LocalDateTime.now())
                .build());
        return result;
    }

    /**
     * 错误切面
     *
     * @param jp    切入点
     * @param error 错误
     */
    @AfterThrowing(pointcut = "pointCut()", throwing = "error")
    public void afterThrowing(JoinPoint jp, Throwable error) {
        final String signature = jp.getSignature().toLongString();
        StringBuilder argsBuilder = new StringBuilder();
        final Stream<Object> stream = Arrays.stream(jp.getArgs());
        stream.forEach(o -> argsBuilder.append(o.toString()).append(','));
        final String args = argsBuilder.toString();
        log.error("---------------异常捕获----------------");
        log.info("异常方法签名: {}", signature);
        log.info("异常方法入参: {}", args);
        log.info("异常原因: {}", error.getCause());
        log.error("---------------------------------------");

        errorLogService.insertLog(ErrorLog.builder()
                .signature(signature)
                .args(args)
                .error(error.getMessage())
                .createTime(LocalDateTime.now())
                .build());
    }
}
