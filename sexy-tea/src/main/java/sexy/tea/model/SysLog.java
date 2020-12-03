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
 * @since 2020-11-28 上午 11:48
 * desc:
 */
@Data
@Builder
@Table(name = "sys_log")
public class SysLog implements Serializable {
    /**
     * 系统日志表主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 调用的uri
     */
    @Column(name = "uri")
    private String uri;

    /**
     * 调用的方法
     */
    @Column(name = "`method`")
    private String method;

    /**
     * 入参
     */
    @Column(name = "args")
    private String args;

    /**
     * 出参
     */
    // @Column(name = "`result`")
    // private String result;

    /**
     * 耗时
     */
    @Column(name = "`use`")
    private Long use;

    /**
     * 调用方IP地址
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