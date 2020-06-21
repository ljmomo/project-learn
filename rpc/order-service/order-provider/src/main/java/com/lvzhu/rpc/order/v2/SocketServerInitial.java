package com.lvzhu.rpc.order.v2;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lvzhu
 * @Class SocketServerInitial
 * @Description 监听Spring 容器刷新完成事件，容器刷新完成启动一个sockt监听连接并处理连接
 * @Date 2020-06-21 14:46
 * @Email 1796518311@qq.com
 */
@Component
public class SocketServerInitial implements ApplicationListener<ContextRefreshedEvent> {
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8888);
            System.out.println("SocketServerInitial.onApplicationEvent start port:8888");
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.execute(new ProcessorHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
