package sexy.tea.model.bo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 支付宝支付参数
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-11-23 上午 12:14
 * desc:
 */
@Data
public class PaymentBO {

    /**
     * 订单标题
     */
    private String subject;

    /**
     * 订单金额, 单位元, 精确到小数点后两位
     */
    private BigDecimal total = BigDecimal.ZERO;

    /**
     * 订单号
     */
    private String orderId;
}
