package sexy.tea.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 大大大西西瓜皮🍉
 * @date 13:25 2020-09-26
 * description:
 */
@Getter
@Setter
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    // 自定义错误码
    private Integer code;

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
