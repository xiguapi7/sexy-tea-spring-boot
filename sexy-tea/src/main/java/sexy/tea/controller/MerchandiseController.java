package sexy.tea.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sexy.tea.model.Merchandise;
import sexy.tea.model.common.Result;
import sexy.tea.service.MerchandiseService;
import sexy.tea.utils.UploadUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 大大大西西瓜皮🍉
 * @date 21:29 2020-09-26
 * description:
 */
@RestController
@RequestMapping("/merchandise")
@Slf4j
public class MerchandiseController {

    private final MerchandiseService service;

    @Autowired
    public MerchandiseController(MerchandiseService service) {
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
    public Result save(@RequestBody Merchandise merchandise) {
        return service.saveOrUpdate(merchandise);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Merchandise merchandise) {
        return service.saveOrUpdate(merchandise);
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
