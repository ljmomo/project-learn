package com.lvzhu.rpc.order.v2;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lvzhu
 * @Class InitialMerdiator
 * @Description 描述
 * @Date 2020-06-21 14:09
 * @Email 1796518311@qq.com
 */
@Component
public class InitialMerdiator implements BeanPostProcessor {


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //处理注解
        if (bean.getClass().isAnnotationPresent(LvRemoteService.class)){
            Method[] declaredMethods = bean.getClass().getDeclaredMethods();
            List<Method> methodList = Arrays.asList(declaredMethods);
            methodList.stream().forEach(method -> {
               String key =  bean.getClass().getInterfaces()[0].getName() + "." + method.getName();
                System.out.println("InitialMerdiator service:"+key);
                Mediator.map.put(key, BeanMethod.builder().bean(bean).method(method).build());
            });

        }
        return null;
    }
}
