package com.lz.redis.luaandtransaction.transaction;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sy
 * @date 2019年11月09日
 */
@RestController
@RequestMapping("/transaction")
@Api(value = "testTransaction", tags = "testTransaction")
public class TestTransactionController {

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/transaction1")
    @ApiOperation(value = "have transaction")
    public Object testTransaction1() {
        //事务是放在队列里面，原子性操作
        Object object = redisTemplate.execute(new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                operations.opsForValue().set("name", "qinyi");
                operations.opsForValue().set("gender", "male");
                operations.opsForValue().set("age", "19");
                return operations.exec();
            }
        });
        return object;
    }

    @PostMapping("/transaction2")
    @ApiOperation(value = "no transaction")
    public Object testTransaction2() {
        //事务是放在队列里面，原子性操作
        Object object = redisTemplate.execute(new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.opsForValue().set("name", "qinyi");
                operations.opsForValue().set("gender", "male");
                operations.opsForValue().set("age", "19");
                return null;
            }
        });
        return object;
    }

    //todo 此行代码会报错，需要再redisTemplate开启事务，否则报错
    @PostMapping("/transaction3")
    @ApiOperation(value = "no transaction normal")
    public Object testTransaction3() {
        //事务是放在队列里面，原子性操作
        redisTemplate.multi();
        redisTemplate.opsForValue().set("name", "qinyi");
        redisTemplate.opsForValue().set("gender", "male");
        redisTemplate.opsForValue().set("age", "19");
        redisTemplate.exec();
        return null;
    }


}
