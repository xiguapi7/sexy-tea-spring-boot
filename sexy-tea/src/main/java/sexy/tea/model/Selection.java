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
@Table(name = "selection")
public class Selection implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * Selection表id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    /**
     * 产品id
     */
    @Column(name = "product_id")
    private String productId;
    /**
     * 产品名称
     */
    @Column(name = "product_name")
    private String productName;
    /**
     * 产品图片
     */
    @Column(name = "product_image")
    private String productImage;
    /**
     * 主分类
     */
    @Column(name = "category")
    private String category;
    /**
     * 子分类
     */
    @Column(name = "sub_type")
    private String subType;
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