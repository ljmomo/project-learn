package com.lvzhu.rpc.order;

/**
 * @author lvzhu
 * @Class Bootstrap
 * @Description 发布服务的类
 * @Date 2020-06-19 0:14
 * @Email 1796518311@qq.com
 */
public class Bootstrap {
    public static void main(String[] args) {
        OrderService orderService = new OrderServiceImpl();
        RpcProxyServer proxyServer = new RpcProxyServer();
        proxyServer.publisher(orderService,8080);
    }
}
