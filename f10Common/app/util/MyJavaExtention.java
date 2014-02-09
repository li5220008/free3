package util;

import play.templates.JavaExtensions;

/**
 * 这个类用于对模板里的属性的java扩展
 * 注意: 对传入的参数判断空值时不是null, 而是一个 org.codehaus.groovy.runtime.NullObject 对象
 *       也可以对 play.templates.JavaExtensions 现有的函数进行重新定义. 如对 format 函数重新定义, 在模板执行时, 只会执行
 *       你重新定义的函数.
 * User: wenzhihong
 * Date: 12-9-14
 * Time: 下午2:59
 */
public class MyJavaExtention extends JavaExtensions {
}
