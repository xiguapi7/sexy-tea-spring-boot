package sexy.tea.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sexy.tea.model.common.Result;
import sexy.tea.service.SelectionService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-12-2 下午 08:41
 * desc:
 */
@SpringBootTest
class SelectionServiceImplTest {

    @Autowired
    private SelectionService service;

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
    void find() {
        final Result result = service.find(1, 3);
        assertNotNull(result);
    }

    @Test
    void findByPrimaryKey() {
        final Result result = service.findById(1L);
        assertNotNull(result);
    }

    @Test
    void saveOrUpdate() {
    }

    @Test
    void uploadImage() {
    }

    @Test
    void delete() {
    }

    @Test
    void findByName() {
        final Result result = service.findByName("咖啡", 1, 3);
        assertNotNull(result);
    }

    @Test
    void itemsMenu() {
        final Result result = service.itemsMenu(1, 3);
        assertNotNull(result);
    }
}