package sexy.tea.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sexy.tea.model.Beverage;
import sexy.tea.model.common.Result;
import sexy.tea.service.BeverageService;
import sexy.tea.utils.UploadUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 饮品控制器
 *
 * @author 大大大西西瓜皮🍉
 * @date 15:15 2020-09-26
 * description:
 */
@RestController
@RequestMapping("/beverage")
@Slf4j
public class BeverageController {

    private final BeverageService service;

    public BeverageController(BeverageService service) {
        this.service = service;
    }

    @GetMapping("/items/{pageNum}/{pageSize}")
    public Result items(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        return service.find(pageNum, pageSize);
    }

    @GetMapping("/itemsById/{id}")
    public Result itemsById(@PathVariable("id") Long id) {
        return service.findByPrimaryKey(id);
    }

    @GetMapping("/itemsByName/{name}/{pageNum}/{pageSize}")
    public Result itemsByName(@PathVariable("name") String name,
                              @PathVariable("pageNum") int pageNum,
                              @PathVariable("pageSize") int pageSize) {
        return service.findByName(name, pageNum, pageSize);
    }

    @GetMapping("/items/menu/{type}/{pageNum}/{pageSize}")
    public Result itemsMenu(@PathVariable("type") int type, @PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        return service.itemsMenu(type, pageNum, pageSize);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Beverage beverage) {
        return service.saveOrUpdate(beverage);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Beverage beverage) {
        return service.saveOrUpdate(beverage);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @PostMapping("/upload")
    public Result upload(HttpServletRequest request) {
        return UploadUtils.upload(request);
    }
}
