package com.lvzhu.rpc.user;

import java.lang.reflect.Proxy;

/**
 * @author lvzhu
 * @Class RpcProxyClient
 * @Description 描述
 * @Date 2020-06-19 0:18
 * @Email 1796518311@qq.com
 */
public class RpcProxyClient {
    public <T> T clientProxy(final Class<T> interfaceClass,final String host,final int port){
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new RemoteInvocationHandler(host,port));
    }

}
