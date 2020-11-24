package sexy.tea.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sexy.tea.model.bo.PaymentBO;
import sexy.tea.model.common.Result;
import sexy.tea.service.PayService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-11-23 上午 12:11
 * desc:
 */
@RestController
@RequestMapping("/pay")
@Slf4j
public class PayController {

    private final PayService payService;

    @Autowired
    public PayController(PayService payService) {
        this.payService = payService;
    }

    /**
     * 下单支付
     *
     * @param paymentBO 支付参数对象
     * @return 统一处理结果对象
     */
    @PostMapping(path = "/confirm")
    public Result confirm(@RequestBody PaymentBO paymentBO) {
        // 前端从data中获取html片段自行下载
        return payService.confirm(paymentBO);
    }

    @PostMapping("/confirmPage")
    public Object confirmPage(@RequestBody PaymentBO paymentBO) {
        return payService.confirm(paymentBO).getData();
    }

    @PostMapping("/fallback")
    public Result fallback(HttpServletRequest request, HttpServletResponse response) {
        return payService.fallback(request);
    }
}
