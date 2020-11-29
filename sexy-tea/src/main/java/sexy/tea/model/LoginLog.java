package sexy.tea.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-11-28 下午 02:57
 * desc:
 */
@Data
@Builder
@Table(name = "login_log")
public class LoginLog implements Serializable {
    /**
     * 日志表id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "`uid`")
    private Long uid;

    /**
     * 用户名字
     */
    @Column(name = "username")
    private String username;

    /**
     * 用户权限
     */
    @Column(name = "role")
    private String role;

    /**
     * 用户登录后的Session id
     */
    @Column(name = "login_session_id")
    private String loginSessionId;

    /**
     * IP地址
     */
    @Column(name = "ip")
    private String ip;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;
}