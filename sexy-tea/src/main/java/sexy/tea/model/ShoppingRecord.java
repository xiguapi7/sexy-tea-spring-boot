package sexy.tea.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 购物车实体类
 *
 * @author 大大大西西瓜皮🍉
 * @summary
 * @since 2020-11-4 下午 03:07
 */
@Data
@Builder
@Table(name = "shopping_record")
public class ShoppingRecord implements Serializable {
    /**
     * 购物车记录表id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "`uid`")
    private Long uid;

    /**
     * 购物项（JSON存储）
     */
    @Column(name = "items")
    private String items;

    /**
     * 记录状态：1 正常，0 禁用，-1 删除
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}