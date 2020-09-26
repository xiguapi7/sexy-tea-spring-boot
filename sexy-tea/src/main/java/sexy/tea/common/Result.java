package sexy.tea.common;

import lombok.Builder;
import lombok.Data;
import sexy.tea.constant.ResultConstant;

import java.io.Serializable;
import java.util.Optional;

/**
 * 响应结果封装类
 *
 * @author 大大大西西瓜皮🍉
 * @date 02:36 2020-09-26
 * description:
 */
@Data
@Builder
public class Result implements Serializable {

    // 响应码
    private Integer code;

    // 响应消息
    private String msg;

    // 响应数据
    private Object data;

    public static Result result(Integer code, String msg, Object data) {
        return Result.builder()
                .code(code)
                .msg(msg)
                .data(data)
                .build();
    }

    public static Result success() {
        return result(ResultConstant.SUCCESS, ResultConstant.SUCCESS_MSG, Optional.empty());
    }

    public static Result success(Object data) {
        return result(ResultConstant.SUCCESS, ResultConstant.SUCCESS_MSG, data);
    }

    public static Result notFound(Object data) {
        return result(ResultConstant.NOT_FOUND, ResultConstant.NOT_FOUND_MSG, data);
    }

    public static Result notFound() {
        return result(ResultConstant.NOT_FOUND, ResultConstant.NOT_FOUND_MSG, Optional.empty());
    }

    public static Result serverError(Object data) {
        return result(ResultConstant.SERVER_ERROR, ResultConstant.SERVER_ERROR_MSG, data);
    }

    public static Result serverError() {
        return result(ResultConstant.SERVER_ERROR, ResultConstant.SERVER_ERROR_MSG, Optional.empty());
    }

    public static Result networkError(Object data) {
        return result(ResultConstant.NETWORK_ERROR, ResultConstant.NETWORK_ERROR_MSG, data);
    }

    public static Result networkError() {
        return result(ResultConstant.NETWORK_ERROR, ResultConstant.NETWORK_ERROR_MSG, Optional.empty());
    }

    public static Result business(Object data) {
        return result(ResultConstant.BUSINESS_EXCEPTION, ResultConstant.BUSINESS_EXCEPTION_MSG, data);
    }

    public static Result business() {
        return result(ResultConstant.BUSINESS_EXCEPTION, ResultConstant.BUSINESS_EXCEPTION_MSG, Optional.empty());
    }

    public static Result notLogin(Object data) {
        return result(ResultConstant.NOT_LOGIN, ResultConstant.NOT_LOGIN_MSG, data);
    }

    public static Result notLogin() {
        return result(ResultConstant.NOT_LOGIN, ResultConstant.NOT_LOGIN_MSG, Optional.empty());
    }
}
