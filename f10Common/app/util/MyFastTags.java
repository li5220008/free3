package util;

import groovy.lang.Closure;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.reflect.FieldUtils;
import play.Play;
import play.i18n.Messages;
import play.libs.IO;
import play.templates.FastTags;
import play.templates.GroovyTemplate;
import play.templates.JavaExtensions;
import play.utils.HTML;

import java.io.File;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * User: wenzhihong
 * Date: 12-9-14
 * Time: 下午2:47
 */
public class MyFastTags extends FastTags {
    /**
     * 空值
     */
    public static final String EMPTY_VALUE = "--";

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    public static final String F10_DEFAULT = "新进";

    public static final String ZERO_VALUE = "0.00";

    //产生公告下载url
    public static void _bulletinUrl(Map<?,?> args, Closure body, PrintWriter out, GroovyTemplate.ExecutableTemplate template, int fromLine){
        String v = (String) args.get("arg");
        String outStr = "";
        if(StringUtils.isBlank(v)){
            //outStr = " href='#'";
        }else{
            String pre = Play.configuration.getProperty("bulletinAttachPreUrl");
            String url = pre + JavaExtensions.urlEncode(v.replace('\\', '/'));
            outStr = String.format(" href=\"%s\" ", StringUtils.replace(url, "+", "%20")); //jdk自身的实现有bug. 它把空格替换成了 + , 应该是要%20才对.
        }
        out.print(outStr);
    }

    public static void _cutStr(Map<?,?> args, Closure body, PrintWriter out, GroovyTemplate.ExecutableTemplate template, int fromLine){
        String v = (String) args.get("arg");
        if(v == null){
            v = (String) args.get("v");
        }
        int len = (Integer)args.get("len");
        if(len < 1){
            len = 1;
        }
        out.print(CommonUtils.cutString(v, len));
    }

    /**
     * 如果是当天并且不是大于今天的日期的时间则格式化为特定格式。
     * 默认做tostring的处理.
     * 包含参数: v   值
     *          f  可选 格式化字符串
     *          d  默认值. 跟v的类型要匹配
     * @param args
     * @param body
     * @param out
     * @param template
     * @param fromLine
     */
    public static void _sinceVF(Map<?,?> args, Closure body, PrintWriter out, GroovyTemplate.ExecutableTemplate template, int fromLine){
        Object v = args.get("arg");
        if (v == null) {
            v = args.get("v");
        }
        final String f = (String) args.get("f"); //格式化字符串
        final Object d = args.get("d"); //默认值
        if(v == null){
            if(d == null){
                out.print(EMPTY_VALUE);
            }else{
            }   out.print((String)d);
            return;
        }
        if (v instanceof Date) {
            Date val = (Date) v;
            Date now = new Date();
            long delta = (now.getTime() - val.getTime()) / 1000;//差距转换成秒
            if (delta < 24 * 60 * 60 && delta >=0 && f != null) { //如果是当天并且不是大于今天的日期的时间则格式化为特定格式。
                out.print(JavaExtensions.format(val, f));
            }else {
                out.print(JavaExtensions.format(val, DEFAULT_DATE_FORMAT));
            }
            return;
        }
        out.print(v);
        return ;
    }

    /**
     * 处理空值,及数字跟日期的格式化
     * 默认做tostring的处理.
     * 包含参数: v   值
     *          f  可选 格式化字符串. 注意这里可以是格式化数字或日期类型. 注意相应类型的格式化字符串. 如 数字 #,##0.00
     *          d  默认值. 跟v的类型要匹配
     *          u  单位(后缀)  字符串
     *          scale  对于数字来说要扩大的10的次方数. 如 1 表示 * 10, -1表示 * 0.1, 2 表示 * 10 * 10
     */
    public static void _emVF(Map<?,?> args, Closure body, PrintWriter out, GroovyTemplate.ExecutableTemplate template, int fromLine){
        Object v = args.get("arg");
        if (v == null) {
            v = args.get("v");
        }
        final String f = (String) args.get("f"); //格式化字符串
        final Object d = args.get("d"); //默认值
        final String u = args.get("u") == null ? "" : (String) args.get("u");
        final int scale = args.get("scale") == null ? 0 : (Integer)args.get("scale");

        if(v == null){
            if(d == null){
                out.print(EMPTY_VALUE);
            }else{
                objFormate(out, d, f, u, 0, true);
            }
            return ;
        }

        objFormate(out, v, f, u, scale, true);
    }
    //专门提供给F10股本股东的标签，作用是当值为null时显示“新进”,当值为0时显示“0.00”
    public static void _emVF3(Map<?,?> args, Closure body, PrintWriter out, GroovyTemplate.ExecutableTemplate template, int fromLine){
        Long v = (Long)args.get("arg");
        if (v == null) {
            v = (Long)args.get("v");
        }
        final String f = (String) args.get("f"); //格式化字符串
        final Object d = args.get("d"); //默认值
        final String u = args.get("u") == null ? "" : (String) args.get("u");
        final int scale = args.get("scale") == null ? 0 : (Integer)args.get("scale");

        if(v == null){
            if(d == null){
                out.print(F10_DEFAULT);
            }else{
                objFormate(out, d, null, u, 0, true);
            }
            return ;
        }
        if(v == 0){
            if(d == null){
                out.print(ZERO_VALUE);
            }else{
                objFormate(out, d, null, u, 0, true);
            }
            return ;
        }

        objFormate(out, v, f, u, scale, true);
    }

    /**
     * 处理空值,及数字跟日期的格式化
     * 默认做tostring的处理.
     * 包含参数: v   值
     *          f  可选 格式化字符串. 注意这里可以是格式化数字或日期类型. 注意相应类型的格式化字符串. 如 数字 #,##0.00
     *          d  默认值. 跟v的类型要匹配
     *          u  单位(后缀)  字符串
     *          scale  对于数字来说要扩大的10的次方数. 如 1 表示 * 10, -1表示 * 0.1, 2 表示 * 10 * 10
     *          增加了剪切字符串功能：当v的长度大于8个字符时剪切掉后面的
     */
    public static void _emVFSubStr(Map<?,?> args, Closure body, PrintWriter out, GroovyTemplate.ExecutableTemplate template, int fromLine){
        Object v = args.get("arg");
        if (v == null) {
            v = args.get("v");
        }
        final String f = (String) args.get("f"); //格式化字符串
        final Object d = args.get("d"); //默认值
        final String u = args.get("u") == null ? "" : (String) args.get("u");
        final int scale = args.get("scale") == null ? 0 : (Integer)args.get("scale");
        if(v.toString().length()>8){
            v = v.toString().substring(0,8);
        }
        if(v == null){
            if(d == null){
                out.print(EMPTY_VALUE);
            }else{
                objFormate(out, d, f, u, 0, true);
            }
            return ;
        }

        objFormate(out, v, f, u, scale, true);
    }

   public static void _emVF2(Map<?,?> args, Closure body, PrintWriter out, GroovyTemplate.ExecutableTemplate template, int fromLine){
       Object v = args.get("arg");
       if (v == null) {
           v = args.get("v");
       }
       final String f = (String) args.get("f"); //格式化字符串
       final Object d = args.get("d"); //默认值
       final String u = args.get("u") == null ? "" : (String) args.get("u");
       final int scale = args.get("scale") == null ? 0 : (Integer) args.get("scale");

       if (v == null) {
           if (d == null) {
               out.print(EMPTY_VALUE);
           } else {
               objFormate(out, d, f, u, 0, false);
           }
           return;
       }

       objFormate(out, v, f, u, scale, false);
   }

    private static void objFormate(PrintWriter out, Object o, String f, String u, int scale, boolean numZeroIsEmpty) {
        if (o instanceof Number) { //处理数字
            Number val = (Number) o;
           /* if (f == null) {
                if(scale != 0){
                    val = val.doubleValue() * Math.pow(10, scale);
                }
                out.print(val.toString());
                out.print(u);
            } else if ((val instanceof Float
                    || val instanceof Double
                    || val instanceof BigDecimal)
                    && (numZeroIsEmpty && Math.abs(val.doubleValue()) < 1e-10)) {

                out.print(EMPTY_VALUE);
            } else if ((val instanceof Long
                    || val instanceof Integer
                    || val instanceof Short
                    || val instanceof Byte
                    || val instanceof BigInteger)
                    && (numZeroIsEmpty && val.longValue() == 0)) {

                out.print(EMPTY_VALUE);
            } else {
                if(scale != 0){
                    val = val.doubleValue() * Math.pow(10, scale);
                }
                out.print(JavaExtensions.format(val, f));
                out.print(u);
            }*/
            String result = NumbericFomate.instance.format(val,scale,f,EMPTY_VALUE,numZeroIsEmpty);
            out.print(EMPTY_VALUE.equals(result)?result:result+u);
            return;
        }

        if (o instanceof Date) {
            Date val = (Date) o;
            if (f == null) { //如果是日期类型, 且没有指定格式化输出的形式, 则使用默认的格式
                //out.print(val.toString());
                out.print(JavaExtensions.format(val, DEFAULT_DATE_FORMAT));
            }else{
                out.print(JavaExtensions.format(val, f));
            }
            out.print(u);
            return ;
        }

        out.print(HTML.htmlEscape(o.toString()));
        out.print(u);
    }

    /**
     * 直接把文件内容进行输出.
     * fname 是文件的完整路径名. 如 app/views/Application/a.tmpl
     * 可以很好的利用jquery的模板: http://www.cnblogs.com/coffeedeveloper/archive/2012/07/25/2609204.html
     */
    public static void _includeRaw(Map<?,?> args, Closure body, PrintWriter out, GroovyTemplate.ExecutableTemplate template, int fromLine){
        String fname = (String) args.get("arg");
        if (fname == null) {
            fname = (String) args.get("fname");
        }
        File f = Play.getFile(fname);
        String s = IO.readContentAsString(f);
        out.println(s);
    }

    /**
     * 只用于输出财务分析里的项
     */
    public static void _outFinanceItem(Map<?, ?> args, Closure body, PrintWriter out, GroovyTemplate.ExecutableTemplate template, int fromLine) {
        Map<String, ?> map = (Map<String, ?>) args.get("arg");
        List<String> dateList = (List<String>) args.get("dl");
        String field = (String) args.get("field"); //属性
        String format = (String) args.get("f"); //格式化值
        Integer scale = (Integer) args.get("scale");
        if(scale == null){
            scale = 0;
        }
        try {
            for (String d : dateList) {
                out.print("<td width=\"15%\" align=\"right\">");
                if (map.get(d) != null) {
                    objFormate(out, FieldUtils.readField(map.get(d), field), format, "", scale, true);
                } else {
                    out.print(EMPTY_VALUE);
                }
                out.print("</td>");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用于取字段规定长度
     * v : 排序字段
     * l:长度
     */
    public static void _subString(Map<?, ?> args, Closure body, PrintWriter out, GroovyTemplate.ExecutableTemplate template, int fromLine) {
        String v = (String) args.get("v");
        int  l = ((Integer)args.get("l")).intValue();
        String m="";
        if(v==null||v==""){
            v="--";
        }
        if(v.length()>l){
            m=v.substring(0,l);
            m+="…";
            out.print(m);
        }else{
            out.print(v);
        }

    }


    /**
     * 把货币转中文
     * @param args
     * @param body
     * @param out
     * @param template
     * @param fromLine
     */
    public static void _currency2Cn(Map<?, ?> args, Closure body, PrintWriter out, GroovyTemplate.ExecutableTemplate template, int fromLine) {
        String cn = "";
        String currency = (String) args.get("arg");
        if (currency == null) {
            cn = "";
        }
        currency = currency.trim();
        if ("CNY".equalsIgnoreCase(currency)) {
            cn = "人民币";
        } else if ("HKD".equalsIgnoreCase(currency)) {
            cn = "港币";
        } else if ("USD".equalsIgnoreCase(currency)) {
            cn = "美元";
        } else {
            cn = currency;
        }

        out.print(cn);
    }
}
