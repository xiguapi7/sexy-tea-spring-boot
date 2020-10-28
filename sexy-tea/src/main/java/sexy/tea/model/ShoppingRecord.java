package sexy.tea.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shopping_record")
public class ShoppingRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 购物车记录表id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    /**
     * 用户id
     */
    @Column(name = "`uid`")
    private Integer uid;
    /**
     * 商品id
     */
    @Column(name = "goods_id")
    private Integer goodsId;
    /**
     * 商品类型：1 饮品，2 周边
     */
    @Column(name = "`type`")
    private Integer type;
    /**
     * 商品数量
     */
    @Column(name = "`count`")
    private Integer count;
    /**
     * 记录状态：1 正常，0 禁用，-1 删除
     */
    @Column(name = "`status`")
    private Integer status;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}