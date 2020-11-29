package sexy.tea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sexy.tea.model.common.Result;
import sexy.tea.model.dto.OrderDto;
import sexy.tea.service.OrderService;

/**
 * @author 大大大西西瓜皮🍉
 * @date 23:18 2020-10-09
 * description:
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Result create(@RequestBody OrderDto orderDto) {
        return service.createOrder(orderDto);
    }

    @GetMapping("/find/{pageNum}/{pageSize}")
    public Result find(@PathVariable int pageNum, @PathVariable int pageSize) {
        return service.find(pageNum, pageSize);
    }

    @GetMapping("/findByOrderId/{orderId}")
    public Result findByOrderId(@PathVariable String orderId) {
        return Result.success("根据订单ID查询", service.findByOrderId(orderId));
    }

    @GetMapping("/findOrderGoodsByOrderId/{orderId}")
    public Result findOrderGoodsByOrderId(@PathVariable String orderId) {
        return service.findOrderGoodsByOrderId(orderId);
    }

    @DeleteMapping("/delete/{orderId}")
    public Result deleteByOrderId(@PathVariable String orderId) {
        return service.deleteByOrderId(orderId);
    }

    @GetMapping("/findByUid/{uid}")
    public Result findByUid(@PathVariable Long uid) {
        return service.findByUid(uid);
    }
}
