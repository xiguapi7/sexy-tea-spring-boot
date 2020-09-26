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
@ApiModel(value = "sexy-tea-model-ExpenseRecord")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "expense_record")
public class ExpenseRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 费用记录表id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value = "费用记录表id")
    private Integer id;
    /**
     * 用户id
     */
    @Column(name = "`uid`")
    @ApiModelProperty(value = "用户id")
    private Integer uid;
    /**
     * 消费时间
     */
    @Column(name = "consume_date")
    @ApiModelProperty(value = "消费时间")
    private Date consumeDate;
    /**
     * 商店名称
     */
    @Column(name = "store_name")
    @ApiModelProperty(value = "商店名称")
    private String storeName;
    /**
     * 消费商品
     */
    @Column(name = "goods")
    @ApiModelProperty(value = "消费商品")
    private String goods;
    /**
     * 消费总价
     */
    @Column(name = "total_price")
    @ApiModelProperty(value = "消费总价")
    private BigDecimal totalPrice;
    /**
     * 获得星数
     */
    @Column(name = "star_gain")
    @ApiModelProperty(value = "获得星数")
    private Integer starGain;
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