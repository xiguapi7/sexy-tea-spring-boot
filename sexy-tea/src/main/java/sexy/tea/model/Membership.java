package sexy.tea.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "membership")
public class Membership implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 会员表id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    /**
     * 用户表id
     */
    @Column(name = "`uid`")
    private Integer uid;
    /**
     * 会员等级
     */
    @Column(name = "star_level")
    private String starLevel;
    /**
     * 会员等级名称
     */
    @Column(name = "star_level_name")
    private String starLevelName;
    /**
     * 会员星数
     */
    @Column(name = "star_number")
    private Integer starNumber;
    /**
     * 下级会员需要星数
     */
    @Column(name = "next_lev")
    private Integer nextLev;
    /**
     * 会员特权
     */
    @Column(name = "`privileges`")
    private String privileges;
    /**
     * 过期时间
     */
    @Column(name = "expire_date")
    private Date expireDate;
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