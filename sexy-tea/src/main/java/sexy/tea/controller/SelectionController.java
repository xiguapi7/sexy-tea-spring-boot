package sexy.tea.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sexy.tea.model.Selection;
import sexy.tea.model.common.Result;
import sexy.tea.service.SelectionService;
import sexy.tea.utils.UploadUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 大大大西西瓜皮🍉
 * @date 20:40 2020-09-26
 * description:
 */
@RestController
@RequestMapping("/selection")
@Slf4j
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

    @GetMapping("/itemsById/{id}")
    public Result itemsById(@PathVariable("id") Integer id) {
        return service.findByPrimaryKey(id);
    }

    @GetMapping("/itemsByName/{name}/{pageNum}/{pageSize}")
    public Result itemsByName(@PathVariable String name, @PathVariable int pageNum, @PathVariable int pageSize) {
        return service.findByName(name, pageNum, pageSize);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Selection selection) {
        return service.saveOrUpdate(selection);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Selection selection) {
        return service.saveOrUpdate(selection);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @PostMapping("/upload")
    public Result upload(HttpServletRequest request) {
        return UploadUtils.upload(request);
    }
}
