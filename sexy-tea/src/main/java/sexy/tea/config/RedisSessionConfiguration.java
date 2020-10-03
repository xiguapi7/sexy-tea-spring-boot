package sexy.tea.config;

/**
 * session托管到redis
 *
 * @author 大大大西西瓜皮🍉
 * @date 23:05 2020-09-26
 * description:
 */
// @Configuration
// 开启Spring Session Redis支持
/*@EnableRedisHttpSession(
        maxInactiveIntervalInSeconds = 3600,// 过期时间，3600 = 60min
        redisNamespace = "sexyTeaSession",// 自定义命名空间
        flushMode = FlushMode.IMMEDIATE// FlushMode.IMMEDIATE表示只要有更新，就刷新缓存
)*/
public class RedisSessionConfiguration {
}
