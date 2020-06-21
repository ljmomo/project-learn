package com.lvzhu.rpc.user.v2;

import com.lvzhu.rpc.order.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lvzhu
 * @Class TestController
 * @Description 描述
 * @Date 2020-06-21 17:02
 * @Email 1796518311@qq.com
 */
@RestController
public class TestController {

    @LvReference
    private OrderService orderService;

    @GetMapping("/getOrderInfo")
    public String getOrderInfo(){
        return orderService.getOrderInfo("MMMMM");
    }
}
