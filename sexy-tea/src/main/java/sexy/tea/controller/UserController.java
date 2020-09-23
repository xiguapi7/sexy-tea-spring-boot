package sexy.tea.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 大大大西西瓜皮🍉
 * @date 16:35 2020-09-23
 * description:
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @GetMapping("/")
    public String hello() {
        return "hello";
    }
}
