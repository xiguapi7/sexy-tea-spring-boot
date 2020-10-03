package sexy.tea.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author 大大大西西瓜皮🍉
 * @date 14:49 2020-10-03
 * description:
 */
@Configuration
public class MyRedisConfiguration {

    @Autowired
    private RedisTemplate redisTemplate;

    @Bean(name = "myRedisTemplate")
    public RedisTemplate redisTemplateInit() {
        // 设置序列化Key的实例化对象
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 设置序列化Value的实例化对象
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }
}
