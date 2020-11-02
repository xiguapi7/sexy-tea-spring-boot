package sexy.tea.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sexy.tea.config.CustomJsonSerializer;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
     * 价格
     */
    @Column(name = "price")
    @JsonSerialize(using = CustomJsonSerializer.class)
    private Double price;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}