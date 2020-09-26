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
@ApiModel(value = "sexy-tea-model-User")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`user`")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value = "用户id")
    private Integer id;
    /**
     * 用户名
     */
    @Column(name = "username")
    @ApiModelProperty(value = "用户名")
    private String username;
    /**
     * 密码
     */
    @Column(name = "`password`")
    @ApiModelProperty(value = "密码")
    private String password;
    /**
     * 昵称
     */
    @Column(name = "nickname")
    @ApiModelProperty(value = "昵称")
    private String nickname;
    /**
     * 性别
     */
    @Column(name = "gender")
    @ApiModelProperty(value = "性别")
    private Integer gender;
    /**
     * 手机号码
     */
    @Column(name = "phone")
    @ApiModelProperty(value = "手机号码")
    private String phone;
    /**
     * 电子邮箱
     */
    @Column(name = "email")
    @ApiModelProperty(value = "电子邮箱")
    private String email;
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