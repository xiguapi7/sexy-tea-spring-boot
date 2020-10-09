package sexy.tea.model;

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
    private Integer id;
    /**
     * 用户id
     */
    @Column(name = "`uid`")
    private Integer uid;
    /**
     * 星礼卡卡号
     */
    @Column(name = "card_id")
    private Integer cardId;
    /**
     * 星礼卡名称
     */
    @Column(name = "card_name")
    private String cardName;
    /**
     * 星礼卡图片
     */
    @Column(name = "card_image")
    private String cardImage;
    /**
     * 余额
     */
    @Column(name = "balance")
    private BigDecimal balance;
    /**
     * 记录状态：1 正常，0 禁用，-1 删除
     */
    @Column(name = "`status`")
    private Integer status;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;
}