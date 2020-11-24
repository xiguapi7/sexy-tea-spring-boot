package sexy.tea.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sexy.tea.config.CustomJsonSerializer;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 订单实体
 * <p>
 * author 大大大西西瓜皮🍉
 * date 12:44 2020-09-29
 * description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`order`")
public class Order implements Serializable {
    /**
     * 订单表id
     */
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 用户id
     */
    @Column(name = "`uid`")
    private Long uid;

    /**
     * 实际支付价格
     */
    @Column(name = "total")
    @JsonSerialize(using = CustomJsonSerializer.class)
    private Double total;

    /**
     * 是否已经支付：1 已支付，0 未支付，-1 订单失效，2 已发货，3 交易成功，4 交易失败
     */
    @Column(name = "is_pay")
    private Integer isPay;

    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;

    /**
     * 昵称
     */
    @Column(name = "nickname")
    private String nickname;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 支付时间
     */
    @Column(name = "pay_time")
    private Date payTime;

    /**
     * 发货时间
     */
    @Column(name = "consign_time")
    private Date consignTime;

    /**
     * 交易结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 交易完成时间
     */
    @Column(name = "close_time")
    private Date closeTime;

    /**
     * 评价
     */
    @Column(name = "rate")
    private String rate;

    /**
     * 记录状态：1 正常，0 禁用，-1 删除
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}