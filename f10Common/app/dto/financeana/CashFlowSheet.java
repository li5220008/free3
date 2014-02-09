package dto.financeana;

import util.CommonUtils;
import util.FieldOut;

/**
 * 财务分析 财务简表 现金流量表
 * User: wenzhihong
 */
public class CashFlowSheet {
    //输出的json对象. 每个对象包含属性 {cn:中文名, fn:json属性名}
    public static final String fieldOutJson = CommonUtils.fieldOutJson(CashFlowSheet.class);

    //经营性现金流
    @FieldOut(cnName = "经营性现金流", order = 1)
    public double cFFO;

    //投资性现金流
    @FieldOut(cnName = "投资性现金流", order = 2)
    public double cFIA;

    //融资性现金流
    @FieldOut(cnName = "融资性现金流", order = 3)
    public double cFFF;

    //净现金流
    @FieldOut(cnName = "净现金流", order = 4)
    public double freeCashFlow;

    //期初现金及等价物
    @FieldOut(cnName = "期初现金及等价物", order = 5)
    public double initialCashEquivalent;

    //期末现金及等价物
    @FieldOut(cnName = "期末现金及等价物", order = 6)
    public double finalCashEquivalent;

    //结束日期 (yyyy-MM-dd)格式
    @FieldOut(cnName = "报告日期", order = 0)
    public String enddateStr;
}
