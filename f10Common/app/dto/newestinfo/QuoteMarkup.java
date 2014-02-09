package dto.newestinfo;

import com.google.gson.annotations.SerializedName;

/**
 * 最新动态 -> 市场表现 -> 行情涨幅
 * User: wenzhihong
 * Date: 12-9-12
 * Time: 上午10:09
 */
public class QuoteMarkup {
    //代码
    @SerializedName("a")
    public String scode;

    //最新的行情
    @SerializedName("b")
    public Double last;

    //一周的行情
    @SerializedName("w1")
    public Double w1;

    //一月的行情
    @SerializedName("m1")
    public Double m1;

    //三月的行情
    @SerializedName("m3")
    public Double m3;

    //六月的行情
    @SerializedName("m6")
    public Double m6;

    //年初至今的行情
    @SerializedName("un")
    public Double utillNow;
}
