package com.lvzhu.rpc.order.v2;

import com.lvzhu.rpc.order.RpcRequest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lvzhu
 * @Class Mediator
 * @Description 描述
 * @Date 2020-06-21 14:20
 * @Email 1796518311@qq.com
 */
public class Mediator {
    //用来存储发布的服务的实例(服务调用的路由) 可以改造为注册中心 zk redis,nacos
    public static Map<String, BeanMethod> map = new ConcurrentHashMap<>();

    private volatile static Mediator instance;

    private Mediator() {
    }

    ;

    //单例实例
    public static Mediator getInstance() {
        if (instance == null) {
            synchronized (Mediator.class) {
                if (instance == null) {
                    instance = new Mediator();
                }
            }
        }
        return instance;
    }

    /**
     * 通过反射来调用方法
     * @param rpcRequest rpcRequest
     * @return
     */
    public Object processor(RpcRequest rpcRequest) {

        String key = rpcRequest.getClassName() + "." + rpcRequest.getMethodName();
        BeanMethod beanMethod = map.get(key);
        if (Objects.isNull(beanMethod)) {
            return null;
        }
        Object bean = beanMethod.getBean();
        Method method = beanMethod.getMethod();
        try {
           return   method.invoke(bean, rpcRequest.getArgs());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
