package sexy.tea.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author 大大大西西瓜皮🍉
 * @date 21:56 2020-09-26
 * description:
 */
@Data
@Builder
public class UserDto {

    private String username;

    private String password;
}
