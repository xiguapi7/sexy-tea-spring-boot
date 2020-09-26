package sexy.tea.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sexy.tea.service.BeverageService;

/**
 * @author 大大大西西瓜皮🍉
 * @date 15:15 2020-09-26
 * description:
 */
@RestController
@RequestMapping("/beverage")
public class BeverageController {

    private final BeverageService service;

    public BeverageController(BeverageService service) {
        this.service = service;
    }


}
