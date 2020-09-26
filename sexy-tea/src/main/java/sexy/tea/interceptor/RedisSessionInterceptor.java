package sexy.tea.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import sexy.tea.common.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Redis登录拦截器 RedisSessionInterceptor
 *
 * @author 大大大西西瓜皮🍉
 * @date 23:13 2020-09-26
 * description:
 */
@Slf4j
public class RedisSessionInterceptor implements HandlerInterceptor {

    @Autowired
    private  StringRedisTemplate template;

    @Value("redis.user.prefix")
    private String prefix;

    private final String LOGIN_SESSION_ID = "loginSessionId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 无论访问的地址是不是正确的，都进行登录验证，登录成功后的访问再进行分发，404的访问自然会进入到错误控制器中
        HttpSession session = request.getSession();
        if(session.getAttribute(LOGIN_SESSION_ID) != null) {
            String loginSessionId = template.opsForValue()
                    .get(prefix + session.getAttribute(LOGIN_SESSION_ID));

            if (StringUtils.isEmpty(loginSessionId) || loginSessionId.equals(session.getId())) {
                return false;
            }

            log.info("用户已登录, sessionId: " + loginSessionId);
            return true;
        }
        response401(response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private void response401(HttpServletResponse response) {
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        log.error("用户未登录");
        try {
            response.getWriter().print(Result.notLogin());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
