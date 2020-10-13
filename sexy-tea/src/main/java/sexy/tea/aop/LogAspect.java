package sexy.tea.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import sexy.tea.annotations.Log;

import java.lang.reflect.Method;

/**
 * 日志切面和切点
 *
 * @author 大大大西西瓜皮🍉
 * @date 23:34 2020-10-09
 * description:
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("@annotation(sexy.tea.annotations.Log)")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) {
        Object result = new Object();

        long startTime = System.currentTimeMillis();
        try {
            result = point.proceed();
        } catch (Throwable throwable) {
            log.error("日志切面环绕通知异常, 异常信息： {}", throwable.getMessage());
        }
        // 计算方法执行时间
        long time = System.currentTimeMillis() - startTime;
        saveLog(point, time);

        return result;
    }

    private void saveLog(ProceedingJoinPoint point, Long time) {

        // TODO 实例化日志存储实体

        // TODO 填充方法执行的时间

        // 获取Log注解对象
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Log logAnnotation = method.getAnnotation(Log.class);

        if (logAnnotation != null) {
            // TODO 填充注解上的描述, 并填充到实体中
            String value = logAnnotation.value();
        }

        // TODO 获取请求的类名、方法名, 并填充到实体中
        String className = point.getTarget().getClass().getName();
        String methodName = signature.getName();

        // 获取请求的参数值和形参名称, 并拼接为参数字符串填充到实体中
        Object[] args = point.getArgs();
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = discoverer.getParameterNames(method);
        if (args != null && parameterNames != null) {
            StringBuilder params = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                params.append("  ").append(parameterNames[i]).append(": ").append(args[i]);
            }
            // TODO 保存参数字符串, 参数字符串格式如 username: root
            String paramsStr = params.toString();
        }

        // TODO 获取HTTP请求的地址、端口、方法等

        // TODO 调用ORM将实体保存
    }
}
