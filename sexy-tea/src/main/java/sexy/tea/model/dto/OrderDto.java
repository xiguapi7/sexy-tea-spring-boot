package sexy.tea.model.dto;

import lombok.Builder;
import lombok.Data;
import sexy.tea.model.Order;
import sexy.tea.model.OrderGoods;
import sexy.tea.model.OrderShipping;
import sexy.tea.model.vo.UserVO;

import java.util.List;

/**
 * @author 大大大西西瓜皮🍉
 * @date 15:40 2020-10-09
 * description:
 */
@Data
@Builder
public class OrderDto {

    private UserVO userVO;

    private Order order;

    private List<OrderGoods> orderGoodsList;

    private OrderShipping orderShipping;
}
