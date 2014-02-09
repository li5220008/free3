package dto.newestinfo;

import com.google.gson.annotations.SerializedName;

/**
 * 用于画图的财务数据
 * User: wenzhihong
 * Date: 12-11-24
 * Time: 下午2:47
 */
public class FinanceDataShortDto {
    //机构代码
    @SerializedName("a")
    public long institutionId;

    //股票代码
    @SerializedName("b")
    public String scode;

    //主营收入
    @SerializedName("c")
    public Double mainIncome;

    //净利润
    @SerializedName("d")
    public Double netProfit;

    //每股收益
    @SerializedName("e")
    public Double eps;

    //报表日期
    @SerializedName("f")
    public String endDate;

}
