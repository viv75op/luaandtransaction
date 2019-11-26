package com.lz.redis.luaandtransaction.support.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;

/**
 * @author sy
 * @date 2019年11月13日
 */
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorCode {

    /**
     * 错误码
     */
    SUCCEED_CODE(200000, "Success"),
    SYSTEM_ERROR(500000, "System Exceptions.");

    private Integer code;
    private String desc;

    public static String getDesc(String code) {
        for (ErrorCode errorCode : ErrorCode.values()) {
            if (errorCode.code.equals(code)) {
                return errorCode.desc;
            }
        }
        return code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getRespCode() {
        return code;
    }

    public String getRespMsg() {
        return desc;
    }
}
