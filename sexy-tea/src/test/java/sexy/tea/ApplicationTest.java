package sexy.tea;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sexy.tea.service.OrderService;

@SpringBootTest
class ApplicationTest {

    @Autowired
    private OrderService service;

    @Test
    void testCreateOrder() {
    }
}
