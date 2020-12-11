package sexy.tea.model;

import lombok.Builder;
import lombok.Data;
import sexy.tea.model.vo.OrderVO;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-12-11 上午 11:16
 * desc:
 */
@Data
@Builder
public class Dashboard implements Serializable {

    private Long userCount;

    private Long storeCount;

    private Double purchases;

    private Long beverageCount;

    private Long selectionCount;

    private Long foodCount;

    private List<OrderVO> orderVOList;

    // private List<CityVO> cityVOList;
}
