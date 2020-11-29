package sexy.tea.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-11-29 上午 11:20
 * desc:
 */
@Data
@Builder
@Table(name = "news_info")
public class NewsInfo implements Serializable {
    /**
     * 星巴克新闻表id
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 新闻标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 新闻超链接
     */
    @Column(name = "link")
    private String link;

    /**
     * 新闻缩略图
     */
    @Column(name = "head_image")
    private String headImage;

    /**
     * 新闻的时间
     */
    @Column(name = "news_times")
    private LocalDateTime newsTimes;

    /**
     * 爬取新闻的时间
     */
    @Column(name = "get_time")
    private LocalDateTime getTime;

    private static final long serialVersionUID = 1L;
}