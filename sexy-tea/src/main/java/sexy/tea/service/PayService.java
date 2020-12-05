package sexy.tea.service;

import sexy.tea.model.bo.PaymentBO;
import sexy.tea.model.common.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * 支付服务接口
 *
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-11-23 上午 12:12
 * desc:
 */
public interface PayService {

    Result confirm(PaymentBO paymentBO);

    Result callback(HttpServletRequest request);
}
