package ynu.it.linyun.server.common.exception;

/**
 * @author linyun
 * @create 2021-04-21-15:32
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -1615638578502184555L;

    public BusinessException(String message) {
        super(message);
    }
}
