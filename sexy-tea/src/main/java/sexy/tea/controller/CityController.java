package sexy.tea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sexy.tea.model.common.Result;
import sexy.tea.service.CityService;

/**
 * @author 大大大西西瓜皮🍉
 * @date 17:51 2020-09-26
 * description:
 */
@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService service;

    @Autowired
    public CityController(CityService service) {
        this.service = service;
    }

    @GetMapping("/items")
    public Result items() {
        return service.find();
    }
}
