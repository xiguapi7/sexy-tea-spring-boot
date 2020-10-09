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
@Table(name = "login_log")
public class LoginLog implements Serializable {
    private static final long serialVersionUID = 1L;
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
    private Integer uid;
    /**
     * 用户名字
     */
    @Column(name = "username")
    private String username;
    /**
     * 登录地址
     */
    @Column(name = "`location`")
    private String location;
    /**
     * IP地址
     */
    @Column(name = "ip")
    private String ip;
    /**
     * 设备
     */
    @Column(name = "device")
    private String device;
    /**
     * 操作时间
     */
    @Column(name = "operation_time")
    private Date operationTime;
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