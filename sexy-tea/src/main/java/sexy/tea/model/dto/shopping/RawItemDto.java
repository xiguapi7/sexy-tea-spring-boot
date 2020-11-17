package sexy.tea.model.dto.shopping;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-11-17 上午 07:56
 * desc:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RawItemDto extends ItemDto {

    protected String beverageId;

    protected String beverageName;

    protected String beverageImage;

    protected Integer type;

    protected String foodId;

    protected String foodName;

    protected String foodImage;

    protected String productId;

    protected String productName;

    protected String productImage;
}
