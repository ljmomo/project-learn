package com.lvzhu.rpc.order.v2;
import org.springframework.stereotype.Component;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lvzhu
 * @Class LvRemoteService
 * @Description 描述
 * @Date 2020-06-21 14:02
 * @Email 1796518311@qq.com
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface LvRemoteService {
}
