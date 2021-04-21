package ynu.it.linyun.server.common.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @author linyun
 * @create 2021-04-21-14:46
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result implements Serializable {
    private String code;
    private String msg;
    private Object data;

    private Result(String code) {
        this.code = code;
    }

    public static Result success() {
        return new Result("200");
    }

    public static Result fail(String code) {
        return new Result(code);
    }

    public Result data(Object data) {
        this.data = data;
        return this;
    }

    public Result msg(String msg) {
        this.msg = msg;
        return this;
    }
}
