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
import java.math.BigDecimal;
import java.util.Date;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@ApiModel(value = "sexy-tea-model-OrderGoods")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_goods")
public class OrderGoods implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 订单商品表
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value = "订单商品表")
    private Integer id;
    /**
     * 订单id
     */
    @Column(name = "order_id")
    @ApiModelProperty(value = "订单id")
    private Integer orderId;
    /**
     * 商品id
     */
    @Column(name = "goods_id")
    @ApiModelProperty(value = "商品id")
    private Integer goodsId;
    /**
     * 商品数量
     */
    @Column(name = "`count`")
    @ApiModelProperty(value = "商品数量")
    private Integer count;
    /**
     * 商品单价
     */
    @Column(name = "price")
    @ApiModelProperty(value = "商品单价")
    private BigDecimal price;
    /**
     * 商品类型：1 饮品，2 周边
     */
    @Column(name = "`type`")
    @ApiModelProperty(value = "商品类型：1 饮品，2 周边")
    private Integer type;
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