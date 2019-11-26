package com.lz.redis.luaandtransaction.support.exception;

import com.lz.redis.luaandtransaction.support.enums.ErrorCode;

/**
 * BaseException
 *
 * @author jiwen2c
 * @version $Id: BaseException.java, v 0.1
 * @date 2019-10-31 21:50
 */
public class BaseException extends RuntimeException {

    /**
     * code
     */
    private Integer code;

    /**
     * message
     */
    private String message;

    /**
     * default constructor
     */
    public BaseException() {

        super();
        this.code = ErrorCode.SYSTEM_ERROR.getRespCode();
        this.message = ErrorCode.SYSTEM_ERROR.getRespMsg();
    }

    /**
     * constructor with given code and message
     *
     * @param code
     * @param message
     */
    public BaseException(Integer code, String message) {

        super(message);
        this.code = code;
        this.message = message;
    }

    /**
     * constructor with given code and message
     *
     * @param code
     * @param message
     */
    public BaseException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public BaseException(Throwable cause) {
        super(cause);
    }


    /**
     * code getter
     *
     * @return
     */
    public Integer getCode() {
        return code;
    }

    /**
     * code setter
     *
     * @param code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * message getter
     *
     * @return
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * message setter
     *
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
