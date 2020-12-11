package sexy.tea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 茶颜粤色后端项目启动类
 *
 * @author 大大大西西瓜皮🍉
 * @date 16:24 2020-09-23
 * description:
 */
@SpringBootApplication
@MapperScan("sexy.tea.mapper")
/*@EnableCaching
@EnableRedisHttpSession(
        maxInactiveIntervalInSeconds = 3600,// 过期时间，3600 = 60min
        redisNamespace = "sexyTeaSession",// 自定义命名空间
        flushMode = FlushMode.IMMEDIATE// FlushMode.IMMEDIATE表示只要有更新，就刷新缓存
)*/
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
