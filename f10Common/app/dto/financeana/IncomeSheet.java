package dto.financeana;

import util.CommonUtils;
import util.FieldOut;

/**
 * 财务分析 财务简表 利润表
 * User: wenzhihong
 */
public class IncomeSheet {
    //输出的json对象. 每个对象包含属性 {cn:中文名, fn:json属性名}
    public static final String fieldOutJson = CommonUtils.fieldOutJson(IncomeSheet.class);

    //营业总收入
    @FieldOut(cnName = "营业总收入", order = 1)
    public double grossRevenue;

    //营业收入
    @FieldOut(cnName = "营业收入", order = 2)
    public double taking;

    //营业利润
    @FieldOut(cnName = "营业利润", order = 3)
    public double operatingProfit;

    //利润总额
    @FieldOut(cnName = "利润总额", order = 4)
    public double totalProfit;

    //净利润
    @FieldOut(cnName = "净利润", order = 5)
    public double retainedProfits;

    //结束日期 yyyy-MM-dd 格式
    @FieldOut(cnName = "报告日期", order = 0)
    public String enddateStr;
}
