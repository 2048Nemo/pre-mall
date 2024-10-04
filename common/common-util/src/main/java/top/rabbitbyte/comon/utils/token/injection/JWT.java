package top.rabbitbyte.comon.utils.token.injection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface JWT {

    boolean required() default false;   //验证没通过时是否拒绝访问

    boolean checkExpired() default true;    //是否允许过期
}