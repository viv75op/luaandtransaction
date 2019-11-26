package com.lz.redis.luaandtransaction.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author sy
 * @date 2019年11月09日
 */
@RestController
@RequestMapping("/lua")
@Api(value = "testLua", tags = "testLua")
public class LuaController {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final DefaultRedisScript<List> LUA_SCRIPT;

    static {
        LUA_SCRIPT = new DefaultRedisScript<>();
        LUA_SCRIPT.setResultType(List.class);
        LUA_SCRIPT.setScriptText(
                "local key=KEYS[1]" +
                        "local key2=KEYS[2]" +
                        "local value=KEYS[2]" +
                        "local value2=ARGV[1]" +
                        "redis.call('hset',key,value,value2)" +
                        "redis.call('hset',key2,value,value2)" +
                        "local return1=redis.call('hvals',key)" +
                        "local return2=redis.call('hkeys',key2)" +
                        "local aa={}" +
                        "for i=1,table.getn(return1),1 do \n" +
                        "aa[i]=return1[i]" +
                        "end \n" +
                        "local aaSize=table.getn(aa)" +
                        "for j=1,table.getn(return2),1 do \n" +
                        "aa[aaSize+j]=return2[j]" +
                        "end \n" +
                        "return aa");
    }

    @PostMapping("/lua1")
    @ApiOperation(value = "lua1")
    public Object testLua1() {
        //此时此脚本里面所有redis操作为原子操作，不会被其他redis操作穿插其间lua脚本写法，
        // 请查阅资料https://www.runoob.com/lua/lua-arrays.html
        return redisTemplate.execute(LUA_SCRIPT, Arrays.asList("key1", "key2"), Collections.singletonList("nihaoma"));
    }

}
