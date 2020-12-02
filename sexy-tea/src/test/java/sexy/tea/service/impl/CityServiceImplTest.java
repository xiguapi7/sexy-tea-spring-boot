package sexy.tea.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sexy.tea.model.common.Result;
import sexy.tea.service.CityService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-12-2 下午 08:27
 * desc:
 */
@SpringBootTest
class CityServiceImplTest {

    @Autowired
    private CityService service;

    @Test
    void find() {
        final Result result = service.find(1, 5);
        assertNotNull(result);
    }

    @Test
    void findByCityName() {
        final Result result = service.findByCityName(1, 5, "湛江");
        assertNotNull(result);
    }

    @Test
    void search() {
        final Result result = service.search(1, 5, "湛江");
        assertNotNull(result);
    }
}