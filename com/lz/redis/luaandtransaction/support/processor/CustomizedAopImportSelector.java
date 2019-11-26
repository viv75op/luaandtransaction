package com.lz.redis.luaandtransaction.support.processor;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 描述:
 * 自定义aop实现，提交给spring处理的类
 *
 * @author baomw
 * @create 2018-11-18 下午 11:29
 */
public class CustomizedAopImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{RealizedAopBeanPostProcessor.class.getName(),RegisterBeanFactoryPostProcessor.class.getName()};
    }
}