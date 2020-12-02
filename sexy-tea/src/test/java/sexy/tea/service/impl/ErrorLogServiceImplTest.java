package sexy.tea.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sexy.tea.model.ErrorLog;
import sexy.tea.model.common.Result;
import sexy.tea.service.ErrorLogService;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-12-2 下午 08:32
 * desc:
 */
@SpringBootTest
class ErrorLogServiceImplTest {

    @Autowired
    private ErrorLogService service;

    @Test
    void find() {
        final Result result = service.find(1, 3);
        assertNotNull(result);
    }

    @Test
    void insertLog() {
        service.insertLog(ErrorLog.builder()
                .error("测试数据")
                .signature("测试数据")
                .args("测试数据")
                .createTime(LocalDateTime.now())
                .build());
    }
}