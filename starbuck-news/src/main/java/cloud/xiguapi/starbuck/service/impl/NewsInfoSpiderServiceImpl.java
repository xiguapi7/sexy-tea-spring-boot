package cloud.xiguapi.starbuck.service.impl;

import cloud.xiguapi.starbuck.pipeline.MySqlPipeline;
import cloud.xiguapi.starbuck.processor.NewsInfoProcessor;
import cloud.xiguapi.starbuck.service.SpiderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

/**
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-11-29 上午 10:56
 * desc:
 */
@Service
@Slf4j
public class NewsInfoSpiderServiceImpl implements SpiderService {

    private final MySqlPipeline pipeline;

    private final NewsInfoProcessor processor;

    @Value("${starbuck.news}")
    private String newsUrl;

    @Autowired
    public NewsInfoSpiderServiceImpl(MySqlPipeline pipeline, NewsInfoProcessor processor) {
        this.pipeline = pipeline;
        this.processor = processor;
    }

    @Override
    public void getData() {
        log.info("--------------------开始爬取新闻数据--------------------");

        Spider.create(processor)
                // 初始化爬取数据的url
                .addUrl(newsUrl)
                // 使用布隆过滤器过滤重复url
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))
                // 设置线程数
                .thread(30)
                // 设置持久化
                .addPipeline(pipeline)
                .run();

        log.info("--------------------爬取新闻数据结束--------------------");
    }
}
