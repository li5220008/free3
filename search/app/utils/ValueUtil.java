package utils;

import com.google.common.collect.Lists;
import play.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * User: 刘建力(liujianli@gtadata.com))
 * Date: 13-3-26
 * Time: 下午5:50
 * 功能描述:
 */
public class ValueUtil {
    public static <T> T getIfEmpty(Object value, T defaultValue, ValueType type) {
        if (!(value instanceof String)) {
            Logger.warn("不是String类型");
        }
        String castValue = String.valueOf(value);
        if (castValue != null && !"".equals(castValue.trim()) && !"null".equals(castValue)) {
            switch (type) {
                case INTEGER:
                    return (T) Integer.valueOf(castValue);
                case LONG:
                    return (T) Long.valueOf(castValue);
                case DOUBLE:
                    return (T) Double.valueOf(castValue);
                case STRING:
                    return (T) castValue;
            }
        }
        return defaultValue;
    }

    public static <T> List<T> wrapSingleToList(T value) {
        List<T> list = Arrays.asList(value);
        return list;
    }

    public static Object[] toLowercase(Object[] value) {
        if (value == null) {
            return null;
        } else {
            for(int i = 0 ;i<value.length;i++){
                String val = String.valueOf(value[i]).toLowerCase();
                value[i] = val;
            }
            return value;
        }
    }
    public static Object[] toUpcase(Object[] value) {
        if (value == null) {
            return null;
        } else {
            for(int i = 0 ;i<value.length;i++){
                String val = String.valueOf(value[i]).toUpperCase();
                value[i] = val;
            }
            return value;
        }
    }

    static Pattern P_Html = Pattern.compile("<[^>]*>",Pattern.CASE_INSENSITIVE);
    //从富文本中提取纯文本
    public static String getPlainText(String richText){
        java.util.regex.Matcher m_html = P_Html.matcher(richText);
        String plainText = m_html.replaceAll(""); //过滤html标签
        return plainText;
    }

    static List<String> urlFlags = Lists.newArrayList("www.","http:","ftp.");
    public static boolean  isUrl(String text){
         if(text.trim().length()<4){
             return false;
         }
        String headString  = text.length()<=4?text.substring(0,4).toLowerCase():text.substring(0,5).toLowerCase();
        if(urlFlags.contains(headString)){
            return true;
        }

        return false;
    }

    public enum ValueType {
        INTEGER,
        LONG,
        DOUBLE,
        STRING
    }
}
