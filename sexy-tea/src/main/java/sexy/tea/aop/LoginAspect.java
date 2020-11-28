package sexy.tea.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

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

    @Pointcut("execution(* sexy.tea.controller.UserController.*(..))")
    public void pointCut() {
    }


}
