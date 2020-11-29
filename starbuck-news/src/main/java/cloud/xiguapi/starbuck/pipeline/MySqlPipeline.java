package cloud.xiguapi.starbuck.pipeline;

import cloud.xiguapi.starbuck.model.NewsInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-11-29 上午 10:51
 * desc:
 */
@Component
@Slf4j
public class MySqlPipeline implements Pipeline {


    @Override
    public void process(ResultItems resultItems, Task task) {
        final NewsInfo newsInfo = resultItems.get("newsInfo");
        if (newsInfo != null) {
            // 插入数据到数据库
        }
    }
}
