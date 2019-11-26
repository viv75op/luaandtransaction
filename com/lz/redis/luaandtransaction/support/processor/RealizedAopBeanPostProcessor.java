package com.lz.redis.luaandtransaction.support.processor;

import com.lz.redis.luaandtransaction.support.util.ConfigurationUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;

public class RealizedAopBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        String targetClass = bean.getClass().getName();
        Object object = bean;
        if (ConfigurationUtil.classzzProxyBeanHolder.containsKey(targetClass)) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(object.getClass());
            enhancer.setCallback(new CustomizedProxyInterceptor(ConfigurationUtil.classzzProxyBeanHolder.get(targetClass)));
            object = enhancer.create();
        }
        return object;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}