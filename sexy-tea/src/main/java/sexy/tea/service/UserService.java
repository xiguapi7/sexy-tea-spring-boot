package sexy.tea.service;

import sexy.tea.common.Result;
import sexy.tea.model.User;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
public interface UserService {


    int updateBatch(List<User> list);

    int updateBatchSelective(List<User> list);

    int batchInsert(List<User> list);

    int insertOrUpdate(User record);

    int insertOrUpdateSelective(User record);

    Result check(String loginSessionId);

    Result register(User user);

    Result login(User user, HttpSession session);

    Result logout(String loginSessionId);
}
