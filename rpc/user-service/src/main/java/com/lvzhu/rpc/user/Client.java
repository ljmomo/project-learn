package com.lvzhu.rpc.user;

import com.lvzhu.rpc.order.OrderService;

/**
 * @author lvzhu
 * @Class Client
 * @Description 描述
 * @Date 2020-06-19 0:17
 * @Email 1796518311@qq.com
 */
public class Client {
    public static void main(String[] args) {
        RpcProxyClient proxyClient = new RpcProxyClient();
        OrderService orderService = proxyClient.clientProxy(OrderService.class, "localhost", 8080);
        System.out.println("orderService result:"+orderService.getOrderInfo("MMMM----MMM"));
    }
}
