package com.lvzhu.rpc.user.v2;

import com.lvzhu.rpc.order.RpcRequest;
import com.lvzhu.rpc.user.v1.RpcNetTransport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author lvzhu
 * @Class RemoteInvocationHandler
 * @Description 描述
 * @Date 2020-06-19 0:21
 * @Email 1796518311@qq.com
 */
@Component
public class RemoteInvocationHandler implements InvocationHandler {
    @Value("${service.host}")
    private String host;

    @Value("${service.port}")
    private int port;


    /**
     * 写主要的处理逻辑
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //先建立远程连接
        RpcNetTransport rpcNetTransport=new RpcNetTransport(host,port);
        // 调用哪个接口、 哪个方法、方法的参数
        RpcRequest request=new RpcRequest();
        request.setArgs(args);
        request.setClassName(method.getDeclaringClass().getName());
        request.setTypes(method.getParameterTypes()); //参数的类型
        request.setMethodName(method.getName());
        return rpcNetTransport.send(request);
    }
}
