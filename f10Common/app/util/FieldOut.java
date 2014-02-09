package util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标识属性的输出.在用于一个类的属性按顺序时很有用
 * User: wenzhihong
 * Date: 12-11-28
 * Time: 下午4:32
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldOut {
    //输出顺序, 按从小到大
    int order() default 0;

    //中文名称
    String cnName();
}
