package cn.wanzizoo.crm.util;

/**
 * @program: RBAC
 * @author: LiuFan
 * @create: 2020/10/19 11:26 上午
 * @description: 自定义业务逻辑异常
 **/
public class LogicException extends RuntimeException{
    public LogicException() {
        super();
    }

    public LogicException(String message) {
        super(message);
    }

    public LogicException(String message, Throwable cause) {
        super(message, cause);
    }
}
