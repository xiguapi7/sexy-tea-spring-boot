package sexy.tea.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sexy.tea.config.CustomJsonSerializer;

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
@Table(name = "beverage")
public class Beverage implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 饮料表id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    /**
     * 饮料编号
     */
    @Column(name = "beverage_id")
    private String beverageId;
    /**
     * 饮料名称
     */
    @Column(name = "beverage_name")
    private String beverageName;
    /**
     * 饮料图片
     */
    @Column(name = "beverage_image")
    private String beverageImage;
    /**
     * 类型：1 奶茶，2 咖啡
     */
    @Column(name = "`type`")
    private Integer type;
    /**
     * 价格
     */
    @Column(name = "price")
    @JsonSerialize(using = CustomJsonSerializer.class)
    private Double price;
    /**
     * 记录状态：1 正常，0 禁用，-1 删除
     */
    @Column(name = "`status`")
    private Integer status;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}