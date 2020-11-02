package sexy.tea.controller;

import cn.hutool.crypto.SecureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sexy.tea.model.User;
import sexy.tea.model.common.Result;
import sexy.tea.service.UserService;

import javax.servlet.http.HttpSession;

/**
 * @author 大大大西西瓜皮🍉
 * @date 21:33 2020-09-26
 * description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping(path = "/register")
    public Result register(@RequestBody User user) {
        return service.register(user);
    }

    @PostMapping("/login/{role}")
    public Result login(@RequestBody User user, @PathVariable String role, HttpSession session) {
        user.setPassword(SecureUtil.md5(user.getPassword()));
        return service.login(user, role, session);
    }

    @GetMapping("/checkLogin/{loginSessionId}")
    public Result checkLogin(@PathVariable String loginSessionId) {
        return service.checkLogin(loginSessionId);
    }

    @GetMapping("/logout/{loginSessionId}")
    public Result logout(@PathVariable String loginSessionId) {
        return service.logout(loginSessionId);
    }

    @GetMapping("/items/{pageNum}/{pageSize}")
    public Result items(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        return service.items(pageNum, pageSize, "");
    }

    @GetMapping("/itemsByUserName/{pageNum}/{pageSize}/{username}")
    public Result itemsByUserName(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize, @PathVariable("username") String username) {
        return service.items(pageNum, pageSize, username);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        return service.removeUser(id);
    }

    @PutMapping("/update")
    public Result update(@RequestBody User user) {
        return service.update(user);
    }

}
