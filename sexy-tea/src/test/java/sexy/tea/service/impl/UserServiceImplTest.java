package sexy.tea.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sexy.tea.model.common.Result;
import sexy.tea.service.UserService;

import static java.lang.System.currentTimeMillis;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-12-2 下午 08:47
 * desc:
 */
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService service;

    @Test
    void register() {
    }

    @Test
    void checkLogin() {
    }

    @Test
    void login() {
    }

    @Test
    void logout() {
    }

    @Test
    void items() {
        final long start = currentTimeMillis();
        final Result result = service.items(1, 10, "");
        final long used = currentTimeMillis() - start;
        System.out.println("used: " + used);
        assertNotNull(result);
    }

    @Test
    void removeUser() {
    }

    @Test
    void batchRemoveUser() {
    }

    @Test
    void update() {
    }
}