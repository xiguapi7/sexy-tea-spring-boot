package sexy.tea.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "sexy-tea-model-ShoppingRecord")
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
    @ApiModelProperty(value = "购物车记录表id")
    private Integer id;
    /**
     * 用户id
     */
    @Column(name = "`uid`")
    @ApiModelProperty(value = "用户id")
    private Integer uid;
    /**
     * 商品id
     */
    @Column(name = "goods_id")
    @ApiModelProperty(value = "商品id")
    private Integer goodsId;
    /**
     * 商品类型：1 饮品，2 周边
     */
    @Column(name = "`type`")
    @ApiModelProperty(value = "商品类型：1 饮品，2 周边")
    private Integer type;
    /**
     * 商品数量
     */
    @Column(name = "`count`")
    @ApiModelProperty(value = "商品数量")
    private Integer count;
    /**
     * 记录状态：1 正常，0 禁用，-1 删除
     */
    @Column(name = "`status`")
    @ApiModelProperty(value = "记录状态：1 正常，0 禁用，-1 删除")
    private Byte status;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}