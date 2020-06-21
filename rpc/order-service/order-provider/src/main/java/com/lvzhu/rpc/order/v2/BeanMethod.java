package com.lvzhu.rpc.order.v2;

import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author lvzhu
 * @Class BeanMethod
 * @Description 描述
 * @Date 2020-06-21 14:18
 * @Email 1796518311@qq.com
 */
@Data
@Builder
public class BeanMethod {
    private Object bean;
    private Method method;
}
