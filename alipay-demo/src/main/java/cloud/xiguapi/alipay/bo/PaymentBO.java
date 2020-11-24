package cloud.xiguapi.alipay.bo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 发起支付时的参数对象
 *
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-11-22 下午 11:25
 * desc:
 */
@Data
public class PaymentBO {

    private String subject;

    private BigDecimal total = BigDecimal.ZERO;
}
