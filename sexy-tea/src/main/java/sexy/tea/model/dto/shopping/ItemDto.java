package sexy.tea.model.dto.shopping;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-11-17 上午 07:53
 * desc:
 */
@Data
public class ItemDto implements Serializable {

    protected Long id;

    protected Double price;

    protected Long count;

    protected String table;
}
