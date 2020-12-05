package sexy.tea.service;

import sexy.tea.model.User;
import sexy.tea.model.common.Result;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户接口
 * <p>
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
public interface UserService {

    Result checkLogin(String loginSessionId);

    Result register(User user);

    Result login(User user, String role, HttpSession session);

    Result logout(String loginSessionId);

    Result items(int pageNum, int pageSize, String name);

    Result removeUser(Integer id);

    Result batchRemoveUser(List<Integer> ids);

    Result update(User user);
}
