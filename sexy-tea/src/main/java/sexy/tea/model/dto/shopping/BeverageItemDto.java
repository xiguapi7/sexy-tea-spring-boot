package sexy.tea.model.dto.shopping;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-11-17 上午 08:02
 * desc:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BeverageItemDto extends ItemDto {

    protected String beverageId;

    protected String beverageName;

    protected String beverageImage;

    protected Integer type;
}
