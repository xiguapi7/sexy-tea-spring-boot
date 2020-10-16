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

/**
 *
 * author 大大大西西瓜皮🍉
 * date 18:00 2020-10-13
 * description: 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sys_log")
public class SysLog implements Serializable {
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
    // @Column(name = "`uid`")
    // private Integer uid;

    /**
     * 用户名字
     */
    // @Column(name = "username")
    // private String username;

    /**
     * 登录地址
     */
    // @Column(name = "`location`")
    // private String location;

    /**
     * IP地址
     */
    // @Column(name = "ip")
    // private String ip;

    /**
     * 设备
     */
    // @Column(name = "device")
    // private String device;

    @Column(name = "`remark`")
    private String remark;

    /**
     * 操作
     */
    @Column(name = "`operation`")
    private String operation;

    /**
     * 操作时间
     */
    @Column(name = "operation_time")
    private Long operationTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Long createTime;

    private static final long serialVersionUID = 1L;
}