package com.lz.redis.luaandtransaction.support.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lz.redis.luaandtransaction.dto.Result;
import com.lz.redis.luaandtransaction.support.enums.ErrorCode;
import com.lz.redis.luaandtransaction.support.util.HttpHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sy
 * @date 2019年11月13日
 */
@Slf4j
@RestControllerAdvice
public class GlobleExceptionHandler {
    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BusinessException.class)
    public Result handlePciDataCZQException(BusinessException e) {
        return Result.builder()
                .code(e.getCode())
                .msg(e.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    public JSONObject handleException(HttpServletRequest request, Exception e) {
        log.error("处理请求时出现异常：", e);
        String bodyContext = HttpHelper.getBodyString(request);
        JSONObject jsonObject = JSON.parseObject(bodyContext);
        if (null == jsonObject) {
            jsonObject = new JSONObject();
        }
        jsonObject.put("result", ErrorCode.SYSTEM_ERROR.getCode());
        jsonObject.put("resultInfo", ErrorCode.SYSTEM_ERROR.getDesc());
        return jsonObject;
    }

    @ExceptionHandler(Throwable.class)
    public Result handleThrowable(Throwable e) {
        log.error("程序出现异常(Throwable):", e);
        return Result.builder()
                .code(ErrorCode.SYSTEM_ERROR.getCode())
                .msg(ErrorCode.SYSTEM_ERROR.getDesc())
                .build();
    }
}
