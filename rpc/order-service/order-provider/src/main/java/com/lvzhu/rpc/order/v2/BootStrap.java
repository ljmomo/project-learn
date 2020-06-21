package com.lvzhu.rpc.order.v2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author lvzhu
 * @Class BootStrap
 * @Description 描述
 * @Date 2020-06-21 15:07
 * @Email 1796518311@qq.com
 */
@Configuration
@ComponentScan("com.lvzhu.rpc.order.v2")
public class BootStrap {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(BootStrap.class).refresh();

    }
}
