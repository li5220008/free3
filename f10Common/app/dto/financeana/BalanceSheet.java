package dto.financeana;

import util.CommonUtils;
import util.FieldOut;

/**
 * 财务分析 财务简表 资产负债表
 * edit: wenzhihong
 */
public class BalanceSheet {
    //输出的json对象. 每个对象包含属性 {cn:中文名, fn:json属性名}
    public static final String fieldOutJson = CommonUtils.fieldOutJson(BalanceSheet.class);

    //流动资产
    @FieldOut(cnName = "流动资产", order = 1)
    public double currentAssets;

    //非流动资产
    @FieldOut(cnName = "非流动资产", order = 2)
    public double noncurrentAssets;

    //资产总计
    @FieldOut(cnName = "资产总计", order = 3)
    public double totalAssets;

    //流动负债
    @FieldOut(cnName = "流动负债", order = 4)
    public double currentLiabilities;

    //非流动负债
    @FieldOut(cnName = "非流动负债", order = 5)
    public double noncurrentLiabilities;

    //负债合计
    @FieldOut(cnName = "负债合计", order = 6)
    public double totalLiabilities;

    //归属于母公司股东权益
    @FieldOut(cnName = "归属于母公司股东权益", order = 7)
    public double stockholderEquity;

    //少数股东权益
    @FieldOut(cnName = "少数股东权益", order = 8)
    public double fewStockholderEquity;

    //负债和股东权益
    @FieldOut(cnName = "负债和股东权益", order = 9)
    public double liabiStockholderEquity;

    //结束日期的 yyyy-MM-dd 格式化形式
    @FieldOut(cnName = "报告日期", order = 0)
    public String enddateStr;

}
