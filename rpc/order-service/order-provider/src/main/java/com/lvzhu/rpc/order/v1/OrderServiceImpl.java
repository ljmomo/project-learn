package com.lvzhu.rpc.order.v1;

import com.lvzhu.rpc.order.OrderService;
/**
 * @author lvzhu
 * @Class OrderServiceImpl
 * @Description 描述
 * @Date 2020-06-18 23:48
 * @Email 1796518311@qq.com
 */
public class OrderServiceImpl implements OrderService  {
    /**
     * 获取订单详情实现
     * @param orderNo orderNo
     * @return String
     */
    @Override
    public String getOrderInfo(String orderNo) {
        return "LVZHU ORDER INFO ";
    }
}
