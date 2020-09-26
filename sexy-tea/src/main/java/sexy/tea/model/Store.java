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
@ApiModel(value = "sexy-tea-model-Store")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "store")
public class Store implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 商店表id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value = "商店表id")
    private Integer id;
    /**
     * 品牌
     */
    @Column(name = "brand")
    @ApiModelProperty(value = "品牌")
    private String brand;
    /**
     * 国家
     */
    @Column(name = "country")
    @ApiModelProperty(value = "国家")
    private String country;
    /**
     * 城市
     */
    @Column(name = "city")
    @ApiModelProperty(value = "城市")
    private String city;
    /**
     * 城市经度
     */
    @Column(name = "center_longitude")
    @ApiModelProperty(value = "城市经度 ")
    private String centerLongitude;
    /**
     * 城市纬度
     */
    @Column(name = "center_latitude")
    @ApiModelProperty(value = "城市纬度")
    private String centerLatitude;
    /**
     * 商店编号
     */
    @Column(name = "store_number")
    @ApiModelProperty(value = "商店编号")
    private String storeNumber;
    /**
     * 商店序列号
     */
    @Column(name = "store_serial")
    @ApiModelProperty(value = "商店序列号")
    private String storeSerial;
    /**
     * 商店名称
     */
    @Column(name = "store_name")
    @ApiModelProperty(value = "商店名称")
    private String storeName;
    /**
     * 商店地址
     */
    @Column(name = "store_address")
    @ApiModelProperty(value = "商店地址")
    private String storeAddress;
    /**
     * 门店经度
     */
    @Column(name = "longitude")
    @ApiModelProperty(value = "门店经度")
    private String longitude;
    /**
     * 门店纬度
     */
    @Column(name = "latitude")
    @ApiModelProperty(value = "门店纬度")
    private String latitude;
    /**
     * 门店电话号码
     */
    @Column(name = "phone_number")
    @ApiModelProperty(value = "门店电话号码")
    private String phoneNumber;
    /**
     * 邮编
     */
    @Column(name = "post_code")
    @ApiModelProperty(value = "邮编")
    private String postCode;
    /**
     * 所有权类型
     */
    @Column(name = "owner_ship_type")
    @ApiModelProperty(value = "所有权类型")
    private String ownerShipType;
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