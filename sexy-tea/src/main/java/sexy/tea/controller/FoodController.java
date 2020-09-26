package sexy.tea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sexy.tea.common.Result;
import sexy.tea.service.FoodService;

/**
 * @author 大大大西西瓜皮🍉
 * @date 21:17 2020-09-26
 * description:
 */
@RestController
@RequestMapping("/food")
public class FoodController {

    private final FoodService service;

    @Autowired
    public FoodController(FoodService service) {
        this.service = service;
    }

    @GetMapping("/items")
    public Result items() {
        return service.find();
    }
}
