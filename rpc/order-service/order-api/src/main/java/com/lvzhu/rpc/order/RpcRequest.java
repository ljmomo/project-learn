package com.lvzhu.rpc.order;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lvzhu
 * @Class RpcRequest
 * @Description 描述
 * @Date 2020-06-18 23:13
 * @Email 1796518311@qq.com
 */
@Data
public class RpcRequest implements Serializable {

    /**
     * 类名称
     */
    private String className;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 参数
     */
    private Object[] args;

    /**
     * 类型
     */
    private Class[] types;
}
