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
 * @since 2020-11-28 下午 02:42
 * desc:
 */
@Data
@Builder
@Table(name = "error_log")
public class ErrorLog implements Serializable {
    /**
     * 错误日志表id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 方法签名
     */
    @Column(name = "signature")
    private String signature;

    /**
     * 入参
     */
    @Column(name = "args")
    private String args;

    /**
     * 错误原因
     */
    @Column(name = "error")
    private String error;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;
}