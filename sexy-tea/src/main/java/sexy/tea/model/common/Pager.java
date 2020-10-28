package sexy.tea.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 *
 * @author 大大大西西瓜皮🍉
 * @summary desc:
 * @since 2020-10-28 下午 04:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pager<T> implements Serializable {

    private int pageNum;

    private int pageSize;

    private long total;

    private List<T> result;
}
