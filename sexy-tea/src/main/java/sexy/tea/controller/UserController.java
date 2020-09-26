package sexy.tea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sexy.tea.common.Result;
import sexy.tea.model.User;
import sexy.tea.service.UserService;

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

    @PostMapping("/login")
    public Result login(@RequestBody User user) {

        return null;
    }
}
