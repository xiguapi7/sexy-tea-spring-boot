package sexy.tea.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;
import sexy.tea.config.CustomJsonSerializer;

/**
 * 订单统计VO
 *
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-12-11 上午 11:18
 * desc:
 */
@Data
@Builder
public class OrderVO {

    private String id;

    @JsonSerialize(using = CustomJsonSerializer.class)
    private Double total;

    private Integer isPay;
}
