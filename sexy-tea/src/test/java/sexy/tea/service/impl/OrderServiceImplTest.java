package sexy.tea.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sexy.tea.model.Order;
import sexy.tea.model.common.Result;
import sexy.tea.service.OrderService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-12-2 下午 08:38
 * desc:
 */
@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    private OrderService service;

    @Test
    void find() {
        final Result result = service.find(1, 3);
        assertNotNull(result);
    }

    @Test
    void createOrder() {
    }

    @Test
    void findByOrderId() {
        final Order order = service.findByOrderId("330e84d5a3d44a42b0ba38eb8381b9c1");
    }

    @Test
    void findOrderGoodsByOrderId() {
        final Result result = service.findOrderGoodsByOrderId("330e84d5a3d44a42b0ba38eb8381b9c1");
        assertNotNull(result);
    }

    @Test
    void deleteByOrderId() {
    }

    @Test
    void fallbackUpdateOrder() {
    }

    @Test
    void findByUid() {
        final Result result = service.findByUid(1L);
        assertNotNull(result);
    }
}