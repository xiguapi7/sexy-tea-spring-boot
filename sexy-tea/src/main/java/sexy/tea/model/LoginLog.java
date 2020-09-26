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
@ApiModel(value = "sexy-tea-model-LoginLog")
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
    @ApiModelProperty(value = "日志表id")
    private Long id;
    /**
     * 用户id
     */
    @Column(name = "`uid`")
    @ApiModelProperty(value = "用户id")
    private Integer uid;
    /**
     * 用户名字
     */
    @Column(name = "username")
    @ApiModelProperty(value = "用户名字")
    private String username;
    /**
     * 登录地址
     */
    @Column(name = "`location`")
    @ApiModelProperty(value = "登录地址")
    private String location;
    /**
     * IP地址
     */
    @Column(name = "ip")
    @ApiModelProperty(value = "IP地址")
    private String ip;
    /**
     * 设备
     */
    @Column(name = "device")
    @ApiModelProperty(value = "设备")
    private String device;
    /**
     * 操作时间
     */
    @Column(name = "operation_time")
    @ApiModelProperty(value = "操作时间")
    private Date operationTime;
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