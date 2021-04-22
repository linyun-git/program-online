package ynu.it.linyun.server.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ynu.it.linyun.server.common.result.Result;

/**
 * @author linyun
 * @create 2021-04-21-15:27
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理其他异常
     *
     * @param e 异常信息
     * @return 其他异常信息
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AccidentException.class)
    public Result handler(AccidentException e) {
        return Result.fail(e.getMessage());
    }

    /**
     * 处理业务异常
     *
     * @param e 异常信息
     * @return 业务异常信息
     */
    @ExceptionHandler(BusinessException.class)
    public Result handler(BusinessException e) {
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        return Result.fail("400").msg(objectError.getDefaultMessage());
    }
}
