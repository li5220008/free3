package dto.predictprofit;

import com.google.gson.annotations.SerializedName;

/**
 * 价格项, 包含一个年度的
 * User: wenzhihong
 * Date: 12-12-19
 * Time: 下午4:31
 */
public class PriceIt2 {
    //价格
    @SerializedName("a")
    public double price;

    //日期
    @SerializedName("b")
    public int yearVal;
}
