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
@ApiModel(value = "sexy-tea-model-Beverage")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "beverage")
public class Beverage implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 饮料表id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value = "饮料表id")
    private Integer id;
    /**
     * 饮料编号
     */
    @Column(name = "beverage_id")
    @ApiModelProperty(value = "饮料编号")
    private Integer beverageId;
    /**
     * 饮料名称
     */
    @Column(name = "beverage_name")
    @ApiModelProperty(value = "饮料名称")
    private String beverageName;
    /**
     * 饮料图片
     */
    @Column(name = "beverage_image")
    @ApiModelProperty(value = "饮料图片")
    private String beverageImage;
    /**
     * 类型：1 奶茶，2 咖啡
     */
    @Column(name = "`type`")
    @ApiModelProperty(value = "类型：1 奶茶，2 咖啡")
    private Byte type;
    /**
     * 主分类
     */
    @Column(name = "category")
    @ApiModelProperty(value = "主分类")
    private String category;
    /**
     * 子分类
     */
    @Column(name = "sub_type")
    @ApiModelProperty(value = "子分类")
    private String subType;
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