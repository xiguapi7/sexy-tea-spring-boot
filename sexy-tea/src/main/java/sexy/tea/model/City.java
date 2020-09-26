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
@ApiModel(value = "sexy-tea-model-City")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "city")
public class City implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 城市表id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value = "城市表id")
    private Integer id;
    /**
     * 城市名
     */
    @Column(name = "city")
    @ApiModelProperty(value = "城市名")
    private String city;
    /**
     * 城市经度
     */
    @Column(name = "center_longitude")
    @ApiModelProperty(value = "城市经度")
    private String centerLongitude;
    /**
     * 城市纬度
     */
    @Column(name = "center_latitude")
    @ApiModelProperty(value = "城市纬度")
    private String centerLatitude;
    /**
     * 门店数量
     */
    @Column(name = "`count`")
    @ApiModelProperty(value = "门店数量")
    private Integer count;
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