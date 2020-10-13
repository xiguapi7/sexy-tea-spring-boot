package sexy.tea.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import sexy.tea.model.common.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义异常类
 *
 * @author 大大大西西瓜皮🍉
 * @date 01:13 2020-09-26
 * description:
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //声明要捕获的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public <T> Result defaultExceptionHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        if (e instanceof BusinessException) {
            log.error("业务异常：" + e.getMessage(), this.getClass());
            BusinessException businessException = (BusinessException) e;
            return Result.business(businessException.getMessage());
        }
        //未知错误
        return Result.result(-10, "系统异常：\\n" + e, request.getRequestURL().toString());
    }
}
