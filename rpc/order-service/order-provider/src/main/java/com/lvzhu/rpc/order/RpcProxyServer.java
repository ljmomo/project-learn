package com.lvzhu.rpc.order;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lvzhu
 * @Class RpcProxyServer
 * @Description 描述
 * @Date 2020-06-18 23:54
 * @Email 1796518311@qq.com
 */
public class RpcProxyServer {
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    public void publisher(Object service,int port){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Bootstrap.main"+"端口8080已经启动 等待连接。。。。。");
            while (true){
                //接受客户端请求
                Socket socket = serverSocket.accept();
                executorService.execute(new ProcessorHandler(socket,service));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
