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
@Table(name = "`user`")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;
    /**
     * 密码
     */
    @Column(name = "`password`")
    private String password;
    /**
     * 昵称
     */
    @Column(name = "nickname")
    private String nickname;
    /**
     * 性别
     */
    @Column(name = "gender")
    private Integer gender;
    /**
     * 手机号码
     */
    @Column(name = "phone")
    private String phone;
    /**
     * 电子邮箱
     */
    @Column(name = "email")
    private String email;
    /**
     * 角色
     */
    @Column(name = "role")
    private String role;
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