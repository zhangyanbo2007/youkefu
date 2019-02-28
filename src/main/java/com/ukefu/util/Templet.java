package com.ukefu.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Templet {
    /**
     * 数据表名称注解，默认值为类名称
     * @return
     */
    public String templet();
    
    
}