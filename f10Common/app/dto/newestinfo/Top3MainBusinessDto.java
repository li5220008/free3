package dto.newestinfo;

import com.google.gson.annotations.SerializedName;

/**
 * 前三大主营业务
 * User: wenzhihong
 * Date: 12-11-24
 * Time: 上午8:38
 */
public class Top3MainBusinessDto {
    //股票代码
    @SerializedName("a")
    public String  scode;

    //项目名称
    @SerializedName("b")
    public String itemName;

    //本期收入金额(营业收入)
    @SerializedName("c")
    public Double earnings;

    //本期收入比例(占比)
    @SerializedName("d")
    public Double earningsProportion;

    //本期毛利金额(毛利)
    @SerializedName("e")
    public Double grossMargin;
    
    //本期毛利率(毛利率)
    @SerializedName("f")
    public Double grossMarginRate;
    
    //收入增长率(同比)
    @SerializedName("g")
    public Double incomeGrowthRate;

    //分类
    @SerializedName("h")
    public int category;
}
