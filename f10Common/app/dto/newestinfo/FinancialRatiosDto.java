package dto.newestinfo;

import com.google.gson.annotations.SerializedName;

/**
 * 财务比率
 * User: wenzhihong
 * Date: 12-11-24
 * Time: 下午12:25
 */
public class FinancialRatiosDto {
    @SerializedName("a")
    public String scode;

    //截止日期
    @SerializedName("b")
    public String endDate;

    //基本每股收益(基本每股收益)
    @SerializedName("c")
    public Double eps;

    //稀释每股收益(稀释每股收益)
    @SerializedName("d")
    public Double dilutedEps;

    //每股净资产(每股净资产)
    @SerializedName("e")
    public Double nav;

    //每股经营活动产生的现金流量净额(每股经营活动现金流)
    @SerializedName("f")
    public Double operatingNcfPerShare;

    //净资产收益率B(净资产收益率)
    @SerializedName("g")
    public Double roeb;

    //营业毛利率(毛利率(%))
    @SerializedName("h")
    public Double operatingMarginRatio;

    //净利润增长率B(净利润增长率)
    @SerializedName("i")
    public Double netProfitGrowthB;

    //营业收入增长率B(主营收入增长率)
    @SerializedName("j")
    public Double operatingRevenueGrowthB;

}
