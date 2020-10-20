package sexy.tea.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sexy.tea.model.Food;
import sexy.tea.model.common.Result;
import sexy.tea.service.FoodService;
import sexy.tea.utils.UploadUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 大大大西西瓜皮🍉
 * @date 21:17 2020-09-26
 * description:
 */
@RestController
@RequestMapping("/food")
@Slf4j
public class FoodController {

    private final FoodService service;

    @Autowired
    public FoodController(FoodService service) {
        this.service = service;
    }

    @GetMapping("/items/{pageNum}/{pageSize}")
    public Result items(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        return service.find(pageNum, pageSize);
    }

    @GetMapping("/itemsById/{id}")
    public Result itemsById(@PathVariable("id") Integer id) {
        return service.findByPrimaryKey(id);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Food food) {
        return service.saveOrUpdate(food);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Food food) {
        return service.saveOrUpdate(food);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @PostMapping("/upload")
    public Result upload(HttpServletRequest request) {
        return UploadUtils.upload(request, service);
    }
}
