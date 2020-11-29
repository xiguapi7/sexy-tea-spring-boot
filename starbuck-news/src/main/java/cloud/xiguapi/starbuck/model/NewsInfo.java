package cloud.xiguapi.starbuck.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-11-29 上午 10:42
 * desc:
 */
@Data
@Builder
public class NewsInfo {

    /**
     * 主键
     */
    private Long id;

    /**
     * 新闻标题
     */
    private String title;

    /**
     * 新闻超链接
     */
    private String link;

    /**
     * 新闻首图
     */
    private String headImage;

    /**
     * 新闻时间
     */
    private LocalDateTime newsTime;

    /**
     * 爬取新闻的时间
     */
    private LocalDateTime getTime;
}
