package sexy.tea.model.dto.shopping;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-11-17 上午 08:04
 * desc:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FoodItemDto extends ItemDto {

    protected String foodId;

    protected String foodName;

    protected String foodImage;
}
