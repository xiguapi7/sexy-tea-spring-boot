package sexy.tea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
