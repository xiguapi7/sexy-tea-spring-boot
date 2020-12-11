package sexy.tea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sexy.tea.model.common.Result;
import sexy.tea.service.StatisticsService;

/**
 * 仪表盘控制器
 *
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-12-11 上午 11:47
 * desc:
 */
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final StatisticsService statisticsService;

    @Autowired
    public DashboardController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/info")
    public Result getDashboardInfo() {
        return statisticsService.getDashboardInfo();
    }
}
