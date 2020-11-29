package sexy.tea.service.impl;

import org.springframework.stereotype.Service;
import sexy.tea.mapper.NewsInfoMapper;
import sexy.tea.service.NewsInfoService;

import javax.annotation.Resource;

/**
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-11-29 上午 11:20
 * desc:
 */
@Service
public class NewsInfoServiceImpl implements NewsInfoService {

    @Resource
    private NewsInfoMapper newsInfoMapper;

}
