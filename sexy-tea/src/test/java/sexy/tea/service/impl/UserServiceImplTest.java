package sexy.tea.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sexy.tea.model.common.Result;
import sexy.tea.service.UserService;

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
    void updateBatch() {
    }

    @Test
    void updateBatchSelective() {
    }

    @Test
    void batchInsert() {
    }

    @Test
    void insertOrUpdate() {
    }

    @Test
    void insertOrUpdateSelective() {
    }

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
        final Result result = service.items(1, 3, "");
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