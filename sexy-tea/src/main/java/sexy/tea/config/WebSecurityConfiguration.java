package sexy.tea.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sexy.tea.interceptor.RedisSessionInterceptor;

/**
 * @author 大大大西西瓜皮🍉
 * @date 23:29 2020-09-26
 * description:
 */
@Configuration
public class WebSecurityConfiguration implements WebMvcConfigurer {

    @Bean
    public RedisSessionInterceptor redisSessionInterceptor() {
        return new RedisSessionInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(redisSessionInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/user/login");

    }
}
