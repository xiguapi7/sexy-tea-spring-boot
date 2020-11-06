package sexy.tea.model.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 购物车Dto对象
 *
 * @author 大大大西西瓜皮🍉
 * @summary
 * @since 2020-11-3 下午 02:39
 */
@Data
@Builder
public class ShoppingRecordDto implements Serializable {

    private Long uid;

    private String items;

    private static final long serialVersionUID = 3502856794064721007L;
}
