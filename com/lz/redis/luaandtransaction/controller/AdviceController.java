package com.lz.redis.luaandtransaction.controller;

import com.alibaba.fastjson.JSONObject;
import com.lz.redis.luaandtransaction.dto.RequestWapperDto;
import com.lz.redis.luaandtransaction.dto.Result;
import com.lz.redis.luaandtransaction.support.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author sy
 * @date 2019年11月13日
 */
@RestController
@RequestMapping("/test")
@Api("测试全局异常")
@Slf4j
public class AdviceController {
    @ApiOperation("测试全局异常")
    @GetMapping("/globleBusinessException")
    public Result globleBusinessException() {
        throw new BusinessException(400, "抛异常");
    }

    @ApiOperation("测试全局异常")
    @PostMapping("/globleException")
    public Result globleException(@RequestBody RequestWapperDto wapperDto) throws Exception {
        log.info(JSONObject.toJSONString(wapperDto));
        throw new Exception();
    }

    @ApiOperation("测试全局异常")
    @GetMapping("/globleThrowable")
    public Result globleThrowable() throws Throwable {
        throw new Throwable();
    }
}
