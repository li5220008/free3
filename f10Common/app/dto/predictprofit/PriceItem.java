package dto.predictprofit;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * 价格项
 * User: wenzhihong
 * Date: 12-12-19
 * Time: 下午2:38
 */
public class PriceItem {
    //价格
    @SerializedName("a")
    public double price;

    //日期
    @SerializedName("b")
    public Date  pdate;

    //统计周期
    @SerializedName("c")
    public String priceterm;

    //研报机构名称
    @SerializedName("d")
    public String institutionname;
}
