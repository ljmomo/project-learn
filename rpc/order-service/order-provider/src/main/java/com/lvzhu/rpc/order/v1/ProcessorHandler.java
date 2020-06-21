package com.lvzhu.rpc.order.v1;

import com.lvzhu.rpc.order.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @author lvzhu
 * @Class ProcessorHandler
 * @Description 描述
 * @Date 2020-06-18 23:59
 * @Email 1796518311@qq.com
 */

public class ProcessorHandler implements Runnable {


    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    /**
     * 请求连接
     */
    private Socket socket;
    /**
     * 处理的类（这就是service 实现）
     */
    private Object service;

    @Override
    public void run() {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest) inputStream.readObject();
            Object result = invoke(rpcRequest);
            System.out.println("服务端 ProcessorHandler.run 执行结果：" + result);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(result);
            outputStream.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Object invoke(RpcRequest request) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //通过反射进行服务的调用
        Class clazz = Class.forName(request.getClassName());
        //找到目标方法
        Method method = clazz.getMethod(request.getMethodName(), request.getTypes());
        return method.invoke(service, request.getArgs());
    }
}
