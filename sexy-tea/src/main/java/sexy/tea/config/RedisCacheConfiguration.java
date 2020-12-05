package sexy.tea.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * Redis缓存配置
 *
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-12-5 下午 04:59
 * desc:
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisCacheConfiguration {

    @Bean
    public RedisTemplate<String, Serializable> redisCacheTemplate(LettuceConnectionFactory factory) {
        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        ;
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(factory);
        return template;
    }
}
