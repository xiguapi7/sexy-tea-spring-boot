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
@Table(name = "expense_record")
public class ExpenseRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 费用记录表id
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
     * 消费时间
     */
    @Column(name = "consume_date")
    private Date consumeDate;
    /**
     * 商店名称
     */
    @Column(name = "store_name")
    private String storeName;
    /**
     * 消费商品
     */
    @Column(name = "goods")
    private String goods;
    /**
     * 消费总价
     */
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    /**
     * 获得星数
     */
    @Column(name = "star_gain")
    private Integer starGain;
    /**
     * 记录状态：1 正常，0 禁用，-1 删除
     */
    @Column(name = "`status`")
    private Byte status;
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