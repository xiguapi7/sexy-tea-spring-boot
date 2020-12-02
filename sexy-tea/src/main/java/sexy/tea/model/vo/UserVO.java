package sexy.tea.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 大大大西西瓜皮🍉
 * @date 16:42 2020-09-27
 * description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVO implements Serializable {

    private String loginSessionId;

    private Long id;

    private String username;

    private String nickname;

    private Integer gender;

    private String phone;

    private String email;

    private String role;
}
