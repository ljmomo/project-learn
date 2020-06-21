package com.lvzhu.rpc.order;

/**
 * @author lvzhu
 * @Class OrderService
 * @Description 描述
 * @Date 2020/6/18 22:58
 * @Email 1796518311@qq.com
 */
public interface OrderService {
    /***
     *获取订单详情
     * @param orderNo orderNo
     * @return String
     */
    String getOrderInfo(String  orderNo);
}
