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
@ApiModel(value = "sexy-tea-model-Card")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "card")
public class Card implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 星礼卡表id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value = "星礼卡表id")
    private Integer id;
    /**
     * 用户id
     */
    @Column(name = "`uid`")
    @ApiModelProperty(value = "用户id")
    private Integer uid;
    /**
     * 星礼卡卡号
     */
    @Column(name = "card_id")
    @ApiModelProperty(value = "星礼卡卡号")
    private Integer cardId;
    /**
     * 星礼卡名称
     */
    @Column(name = "card_name")
    @ApiModelProperty(value = "星礼卡名称")
    private String cardName;
    /**
     * 星礼卡图片
     */
    @Column(name = "card_image")
    @ApiModelProperty(value = "星礼卡图片")
    private String cardImage;
    /**
     * 余额
     */
    @Column(name = "balance")
    @ApiModelProperty(value = "余额")
    private BigDecimal balance;
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