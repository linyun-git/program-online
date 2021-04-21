package ynu.it.linyun.server.common.exception;

/**
 * @author linyun
 * @create 2021-04-21-15:33
 */
public class AccidentException extends RuntimeException{
    private static final long serialVersionUID = 5532984349994421828L;

    public AccidentException(String message) {
        super(message);
    }
}
