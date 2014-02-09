package util;

import play.templates.JavaExtensions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * User: 刘建力(liujianli@gtadata.com))
 * Date: 13-1-8
 * Time: 上午11:20
 * 功能描述: 一些数字处理方法
 */
public class NumbericFomate {
   public static NumbericFomate instance = new NumbericFomate();
   private NumbericFomate(){
       if(instance !=null){
           throw new RuntimeException("util.NumbericFomate 已经存在,不能多次初始化");
       }
   }
    public String format(Number value,int scale,String f,String zeroDefaultValue,boolean numZeroIsEmpty){
        String result = null;
        if (f == null) {
            if(scale != 0){
                value = value.doubleValue() * Math.pow(10, scale);
            }
            result =  value.toString();
            //out.print(u);
        } else if ((value instanceof Float
                || value instanceof Double
                || value instanceof BigDecimal)
                && (numZeroIsEmpty && Math.abs(value.doubleValue()) < 1e-10)) {

            result = zeroDefaultValue;
        } else if ((value instanceof Long
                || value instanceof Integer
                || value instanceof Short
                || value instanceof Byte
                || value instanceof BigInteger)
                && (numZeroIsEmpty && value.longValue() == 0)) {

            result =  zeroDefaultValue;
        } else {
            if(scale != 0){
                value = value.doubleValue() * Math.pow(10, scale);
            }

            DecimalFormat df = new DecimalFormat(f);
            df.setRoundingMode(RoundingMode.HALF_UP); //四舍五入
            result = df.format(value);
            //out.print(u);
        }

        return result;
    }
    public String format(Number value,int scale,String f){
        return format(value,scale,f,"",true);
    }
    public  String format(Number value,String f){
        return format(value,0,f,"0.0",true);
    }

}
