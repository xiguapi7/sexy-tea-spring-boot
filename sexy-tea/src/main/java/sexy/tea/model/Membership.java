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
@ApiModel(value = "sexy-tea-model-Membership")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "membership")
public class Membership implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 会员表id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value = "会员表id")
    private Integer id;
    /**
     * 用户表id
     */
    @Column(name = "`uid`")
    @ApiModelProperty(value = "用户表id")
    private Integer uid;
    /**
     * 会员等级
     */
    @Column(name = "star_level")
    @ApiModelProperty(value = "会员等级")
    private String starLevel;
    /**
     * 会员等级名称
     */
    @Column(name = "star_level_name")
    @ApiModelProperty(value = "会员等级名称")
    private String starLevelName;
    /**
     * 会员星数
     */
    @Column(name = "star_number")
    @ApiModelProperty(value = "会员星数")
    private Integer starNumber;
    /**
     * 下级会员需要星数
     */
    @Column(name = "next_lev")
    @ApiModelProperty(value = "下级会员需要星数")
    private Integer nextLev;
    /**
     * 会员特权
     */
    @Column(name = "`privileges`")
    @ApiModelProperty(value = "会员特权")
    private String privileges;
    /**
     * 过期时间
     */
    @Column(name = "expire_date")
    @ApiModelProperty(value = "过期时间")
    private Date expireDate;
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