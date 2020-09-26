package sexy.tea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sexy.tea.mapper.LoginLogMapper;
import sexy.tea.model.LoginLog;
import sexy.tea.service.LoginLogService;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

    private final LoginLogMapper loginLogMapper;

    @Autowired
    public LoginLogServiceImpl(LoginLogMapper loginLogMapper) {
        this.loginLogMapper = loginLogMapper;
    }

    @Override
    public int updateBatch(List<LoginLog> list) {
        return loginLogMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<LoginLog> list) {
        return loginLogMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<LoginLog> list) {
        return loginLogMapper.batchInsert(list);
    }

    @Override
    public int insertOrUpdate(LoginLog record) {
        return loginLogMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(LoginLog record) {
        return loginLogMapper.insertOrUpdateSelective(record);
    }

}
