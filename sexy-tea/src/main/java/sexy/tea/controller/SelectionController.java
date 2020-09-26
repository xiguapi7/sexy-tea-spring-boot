package sexy.tea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sexy.tea.common.Result;
import sexy.tea.service.SelectionService;

/**
 * @author 大大大西西瓜皮🍉
 * @date 20:40 2020-09-26
 * description:
 */
@RestController
@RequestMapping("/selection")
public class SelectionController {

    private final SelectionService service;

    @Autowired
    public SelectionController(SelectionService service) {
        this.service = service;
    }

    @GetMapping("/items/{pageNum}/{pageSize}")
    public Result items(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        return service.find(pageNum, pageSize);
    }
}
