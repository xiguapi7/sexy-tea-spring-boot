package cloud.xiguapi.alipay.controller;

import cloud.xiguapi.alipay.bo.PaymentBO;
import cloud.xiguapi.alipay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-11-22 下午 11:53
 * desc:
 */
@RestController
@RequestMapping("/pay")
public class PayController {

    private final PayService payService;

    @Autowired
    public PayController(PayService payService) {
        this.payService = payService;
    }

    /**
     * 下单支付
     */
    @GetMapping(value = "/confirm", produces = {"text/html;charset=UTF-8"})
    public Object pay(@RequestParam(required = false) PaymentBO bo) throws Exception {
        //这个接口其实应该是post方式的，但是我这里图方便，直接以get方式访问，
        //且返回格式是text/html，这样前端页面就能直接显示支付宝返回的html片段
        //真实场景下由post方式请求，返回code、msg、data那种格式的标准结构，让前端拿到data里的
        //html片段之后自行加载

        bo = new PaymentBO();
        return payService.pay(bo);
    }

    /**
     * 支付成功的回调
     */
    @PostMapping(value = "/fallback")
    public Object fallback(HttpServletRequest request) {
        Map map = request.getParameterMap();
        System.out.println("进入了回调");
        return null;
    }
}
