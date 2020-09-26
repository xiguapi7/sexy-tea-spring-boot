package sexy.tea.constant;

import org.springframework.beans.factory.annotation.Value;

/**
 * Redis常量类
 *
 * @author 大大大西西瓜皮🍉
 * @date 23:47 2020-09-26
 * description:
 */
public class RedisConstant {

    @Value("spring.redis.database")
    public static String REDIS_DATABASE;

    @Value("redis.user.prefix")
    public static String userPrefix;

    @Value("redis.beverage.prefix")
    public static String beveragePrefix;
}
