package com.lvzhu.rpc.user.v2;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author lvzhu
 * @Class ReferenceInvokeProxy
 * @Description 描述
 * @Date 2020-06-21 16:50
 * @Email 1796518311@qq.com
 */
@Component
public class ReferenceInvokeProxy implements BeanPostProcessor {
    @Autowired
    RemoteInvocationHandler invocationHandler;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        if (declaredFields.length>0){
            Arrays.asList(declaredFields).stream().forEach(field -> {
                if (field.isAnnotationPresent(LvReference.class)){
                    System.out.println("需要注入的字段："+bean.getClass()+" "+field.getName());
                    field.setAccessible(true);
                    Object o = Proxy.newProxyInstance(field.getType().getClassLoader(), new Class<?>[]{field.getType()}, invocationHandler);
                    try {
                        field.set(bean,o);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        return null;
    }
}
