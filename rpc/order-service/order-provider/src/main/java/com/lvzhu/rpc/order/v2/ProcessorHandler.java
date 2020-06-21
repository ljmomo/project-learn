package com.lvzhu.rpc.order.v2;

import com.lvzhu.rpc.order.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author lvzhu
 * @Class ProcessorHandler
 * @Description 描述
 * @Date 2020-06-21 14:51
 * @Email 1796518311@qq.com
 */
public class ProcessorHandler implements Runnable {
    private Socket socket;

    public ProcessorHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest request = (RpcRequest) inputStream.readObject();
            Mediator mediator = Mediator.getInstance();
            Object result = mediator.processor(request);
            System.out.println("服务端返回"+result);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(result);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            //关闭流
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
