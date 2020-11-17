package sexy.tea.model.dto.shopping;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 购物车Dto对象
 *
 * @author 大大大西西瓜皮🍉
 * @summary
 * @since 2020-11-3 下午 02:39
 */
@Data
@Builder
public class ShoppingRecordDto {

    private Long uid;

    private List<RawItemDto> items;
}
