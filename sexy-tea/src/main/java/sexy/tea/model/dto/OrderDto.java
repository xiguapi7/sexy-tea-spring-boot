package sexy.tea.model.dto;

import lombok.Builder;
import lombok.Data;
import sexy.tea.model.ShoppingRecord;
import sexy.tea.model.User;

/**
 * @author 大大大西西瓜皮🍉
 * @date 15:40 2020-10-09
 * description:
 */
@Data
@Builder
public class OrderDto {

    private User user;

    private ShoppingRecord record;
}
