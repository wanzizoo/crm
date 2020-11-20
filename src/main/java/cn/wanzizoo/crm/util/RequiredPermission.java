package cn.wanzizoo.crm.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//指定贴的位置
@Target(ElementType.METHOD)
//指定保存的时期:SOURCE,CLASS,RUNTIME
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredPermission {

    String[] value();


}
