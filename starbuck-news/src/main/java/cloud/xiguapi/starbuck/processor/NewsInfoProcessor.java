package cloud.xiguapi.starbuck.processor;

import cloud.xiguapi.starbuck.model.NewsInfo;
import cloud.xiguapi.starbuck.service.SpiderService;
import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-11-29 上午 10:53
 * desc:
 */
@Component
@Slf4j
public class NewsInfoProcessor implements PageProcessor {

    private final SpiderService service;

    //配置爬虫信息
    private final Site site = Site.me()
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36")
            .setCharset("gbk")
            .setTimeOut(10 * 1000)
            .setRetryTimes(3)
            .setRetrySleepTime(3000);


    @Autowired
    public NewsInfoProcessor(SpiderService service) {
        this.service = service;
    }

    @Override
    public void process(Page page) {

        // 解析列表页
        List<Selectable> nodes = page.getHtml().css("section#content ul.list").nodes();

        if (CollUtil.isEmpty(nodes)) {
            this.saveNewsInfo(page);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    private void saveNewsInfo(Page page) {
        // 解析页面
        final Html html = page.getHtml();

        final List<String> liList = html.css("section#content ul.list").all();

        log.info("{}", liList);


        final NewsInfo newsInfo = NewsInfo.builder().build();

        page.putField("newsInfo", newsInfo);
    }
}
