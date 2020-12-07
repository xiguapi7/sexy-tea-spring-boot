package sexy.tea.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sexy.tea.model.common.Result;
import sexy.tea.service.BeverageService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-12-2 下午 08:11
 * desc:
 */
@SpringBootTest
class BeverageServiceImplTest {

    @Autowired
    private BeverageService service;

    @Test
    void find() {
        final Result result = service.find(1, 3);
        assertNotNull(result);
        assertNotNull(result.getData());
    }

    @Test
    void itemsMenu() {
        final Result result = service.itemsMenu(1, 1, 3);
        assertNotNull(result);
        assertNotNull(result.getData());
    }

    @Test
    void findByPrimaryKey() {
        final Result result = service.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getData());
    }

    @Test
    void saveOrUpdate() {
        /*final Result insertResult = service.saveOrUpdate(Beverage.builder()
                .beverageName("烤奶（测试插入）")
                .price(12.00)
                .type(1)
                .beverageImage("citrus-mint-mojito.jpg")
                .status(1)
                .createTime(new Date())
                .updateTime(new Date())
                .build());

        assertNotNull(insertResult);
        final Beverage beverage = (Beverage) insertResult.getData();
        assertNotNull(beverage.getId());

        beverage.setBeverageName("烤奶（测试修改）");
        beverage.setUpdateTime(new Date());

        final Result updateResult = service.saveOrUpdate(beverage);
        assertNotNull(updateResult);*/

    }


    @Test
    void findByName() {
        final Result result = service.findByName("拿铁", 1, 5);
        assertNotNull(result);
    }

    @Test
    void delete() {
        // service.delete(74L);
    }
}