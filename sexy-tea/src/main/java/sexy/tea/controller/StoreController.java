package sexy.tea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sexy.tea.model.common.Result;
import sexy.tea.service.StoreService;

/**
 * @author 大大大西西瓜皮🍉
 * @date 21:09 2020-09-26
 * description:
 */
@RestController
@RequestMapping("/store")
public class StoreController {

    private final StoreService service;

    @Autowired
    public StoreController(StoreService service) {
        this.service = service;
    }

    @GetMapping("/items")
    public Result items() {
        return service.find();
    }
}
