package com.lz.redis.luaandtransaction.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述:
 * 自定义数据结构
 *
 * @author baomw
 * @create 2018-11-19 下午 4:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProxyBeanHolder {
    //通知类名称
    private volatile String className;
    //通知方法名称
    private volatile String methodName;
    //注解类名称
    private volatile String annotationName;
}