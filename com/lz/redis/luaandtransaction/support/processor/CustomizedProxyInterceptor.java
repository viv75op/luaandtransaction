package com.lz.redis.luaandtransaction.support.processor;

import com.lz.redis.luaandtransaction.model.ProxyBeanHolder;
import com.lz.redis.luaandtransaction.support.util.ConfigurationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

@Slf4j
public class CustomizedProxyInterceptor implements MethodInterceptor {
    //用于接收切面信息
    List<ProxyBeanHolder> proxyBeanHolderList;

    public CustomizedProxyInterceptor(List<ProxyBeanHolder> proxyBeanHolderList) {
        this.proxyBeanHolderList = proxyBeanHolderList;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //处理前置及环绕前置通知
        proxyBeanHolderList.stream().filter(proxyBeanHolder -> proxyBeanHolder.getAnnotationName()
                .equals(ConfigurationUtil.BEFORE) || proxyBeanHolder.getAnnotationName().equals(ConfigurationUtil.AROUND)).forEach(this::doProxy);

        Object result = methodProxy.invokeSuper(o, objects);
        //处理前置及环绕前置通知
        proxyBeanHolderList.stream().filter(proxyBeanHolder -> proxyBeanHolder.getAnnotationName()
                .equals(ConfigurationUtil.AFTER) || proxyBeanHolder.getAnnotationName().equals(ConfigurationUtil.AROUND)).forEach(this::doProxy);

        return result;
    }

    /**
     * 处理代理操作
     *
     * @param proxyBeanHolder
     */
    private void doProxy(ProxyBeanHolder proxyBeanHolder) {
        String className = proxyBeanHolder.getClassName();
        String methodName = proxyBeanHolder.getMethodName();
        Object classzz;
        try {
            classzz = Class.forName(className);
            Method[] methods = ((Class) classzz).getMethods();
            for (Method poxyMethod : methods) {
                if (poxyMethod.getName().equals(methodName)) {
                    poxyMethod.invoke(((Class) classzz).newInstance(), null);
                }
            }
        } catch (Exception e) {
            log.error(e.toString(), e);
        }

    }
}