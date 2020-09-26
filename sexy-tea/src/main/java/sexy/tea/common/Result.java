package sexy.tea.common;

import lombok.Builder;
import lombok.Data;
import sexy.tea.constant.ResultConstant;

import java.io.Serializable;

/**
 * 响应结果封装类
 *
 * @author 大大大西西瓜皮🍉
 * @date 02:36 2020-09-26
 * description:
 */
@Data
@Builder
public class Result<T> implements Serializable {

    // 响应码
    private Integer code;

    // 响应消息
    private String msg;

    // 响应数据
    private T data;

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(Integer code) {
        this(code, "", null);
    }

    public Result(Integer code, String msg) {
        this(code, msg, null);
    }

    public Result<T> sucess() {
        return new Result<>(ResultConstant.SUCCESS, ResultConstant.SUCCESS_MSG);
    }

    public Result<T> success(T data) {
        return new Result<>(ResultConstant.SUCCESS, ResultConstant.SUCCESS_MSG, data);
    }

    public Result<T> notFound() {
        return new Result<>(ResultConstant.NOT_FOUND, ResultConstant.NOT_FOUND_MSG);
    }

    public Result<T> notFound(T data) {
        return new Result<>(ResultConstant.NOT_FOUND, ResultConstant.NOT_FOUND_MSG, data);
    }

    public Result<T> serverError() {
        return new Result<>(ResultConstant.SERVER_ERROR, ResultConstant.SERVER_ERROR_MSG);
    }

    public Result<T> serverError(T data) {
        return new Result<>(ResultConstant.SERVER_ERROR, ResultConstant.SERVER_ERROR_MSG, data);
    }

    public Result<T> networkError() {
        return new Result<>(ResultConstant.NETWORK_ERROR, ResultConstant.NETWORK_ERROR_MSG);
    }

    public Result<T> networkError(T data) {
        return new Result<>(ResultConstant.NETWORK_ERROR, ResultConstant.NETWORK_ERROR_MSG, data);
    }
}
