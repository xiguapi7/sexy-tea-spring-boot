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
@Table(name = "coupon")
public class Coupon implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 优惠券表id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    /**
     * 优惠券标题
     */
    @Column(name = "title")
    private String title;
    /**
     * 优惠券开始日期
     */
    @Column(name = "start_date")
    private Date startDate;
    /**
     * 优惠券结束日期
     */
    @Column(name = "expire_date")
    private Date expireDate;
    /**
     * 优惠券使用日期
     */
    @Column(name = "use_date")
    private Date useDate;
    /**
     * 优惠券使用状态
     */
    @Column(name = "use_status")
    private Integer useStatus;
    /**
     * 商品类型
     */
    @Column(name = "goods_type")
    private String goodsType;
    /**
     * 使用类型
     */
    @Column(name = "require_type")
    private String requireType;
    /**
     * 使用数量
     */
    @Column(name = "`require`")
    private Integer require;
    /**
     * 优惠券类型
     */
    @Column(name = "coupon_type")
    private String couponType;
    /**
     * 优惠券数量
     */
    @Column(name = "coupon_num")
    private Integer couponNum;
    /**
     * 优惠券图片
     */
    @Column(name = "coupon_img")
    private String couponImg;
    /**
     * 优惠券规则
     */
    @Column(name = "rules")
    private String rules;
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