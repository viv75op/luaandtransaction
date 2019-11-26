package com.lz.redis.luaandtransaction.support.exception;

import org.slf4j.helpers.MessageFormatter;

/**
 * 公共自定义业务异常
 *
 * <p>
 * detailed comment
 *
 * @author hehao 2019年03月22日
 * @see
 * @since 1.0
 */
public class BusinessException extends BaseException {

    public BusinessException(Integer code, String message) {
        super(code, message);
    }

    public BusinessException(Integer code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(Integer code, String message, String... args) {
        super(code, message);

        if (message.contains("{}")) {
            this.setMessage(MessageFormatter.arrayFormat(message, args).getMessage());
        }
    }

    public BusinessException(Integer code, String message, Throwable cause, String... args) {
        super(code, message, cause);

        if (message.contains("{}")) {
            this.setMessage(MessageFormatter.arrayFormat(message, args).getMessage());
        }
    }
}