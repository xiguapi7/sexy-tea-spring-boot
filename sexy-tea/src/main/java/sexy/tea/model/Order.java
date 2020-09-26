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
@ApiModel(value = "sexy-tea-model-Order")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`order`")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 订单表id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value = "订单表id")
    private Integer id;
    /**
     * 用户id
     */
    @Column(name = "`uid`")
    @ApiModelProperty(value = "用户id")
    private Integer uid;
    /**
     * 订单编号
     */
    @Column(name = "order_number")
    @ApiModelProperty(value = "订单编号")
    private Long orderNumber;
    /**
     * 实际支付价格
     */
    @Column(name = "pay_price")
    @ApiModelProperty(value = "实际支付价格")
    private BigDecimal payPrice;
    /**
     * 是否已经支付：1 已支付，0 未支付，-1 订单失效
     */
    @Column(name = "is_pay")
    @ApiModelProperty(value = "是否已经支付：1 已支付，0 未支付，-1 订单失效")
    private Boolean isPay;
    /**
     * 支付单号
     */
    @Column(name = "pay_id")
    @ApiModelProperty(value = "支付单号")
    private Long payId;
    /**
     * 支付时间
     */
    @Column(name = "pay_time")
    @ApiModelProperty(value = "支付时间")
    private Date payTime;
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