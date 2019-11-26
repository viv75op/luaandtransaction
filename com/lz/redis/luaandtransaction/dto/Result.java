package com.lz.redis.luaandtransaction.dto;

import com.lz.redis.luaandtransaction.support.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sy
 * @date 2019年11月13日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result {
    private Integer code;
    private String msg;
    private Object responseDetail;

    public static Result success() {
        return Result.builder()
                .code(ErrorCode.SUCCEED_CODE.getCode())
                .msg(ErrorCode.SUCCEED_CODE.getDesc())
                .build();
    }

    public static Result fail() {
        return Result.builder()
                .code(ErrorCode.SYSTEM_ERROR.getCode())
                .msg(ErrorCode.SYSTEM_ERROR.getDesc())
                .build();
    }

    public static Result success(Object responseDetail) {
        return Result.builder()
                .code(ErrorCode.SUCCEED_CODE.getCode())
                .msg(ErrorCode.SUCCEED_CODE.getDesc())
                .responseDetail(responseDetail)
                .build();
    }

    public static Result fail(Object responseDetail) {
        return Result.builder()
                .code(ErrorCode.SYSTEM_ERROR.getCode())
                .msg(ErrorCode.SYSTEM_ERROR.getDesc())
                .responseDetail(responseDetail)
                .build();
    }

    public static Result response(Integer code, String msg, Object responseDetail) {
        return Result.builder()
                .code(code)
                .msg(msg)
                .responseDetail(responseDetail)
                .build();
    }
}
