package dto.financing;

import com.google.common.collect.Sets;
import com.google.gson.annotations.SerializedName;

import java.util.Set;

/**
 * 融资融券
 * User: panzhiwei
 * Date: 12-9-20
 * Time: 下午2:55
 */
public class Financing {
    @SerializedName("a")
    public String tradingDate;/*信用交易日期*/

    @SerializedName("b")
    public long  buyValue;/*融资买入额*/

    @SerializedName("c")
    public long  repayValue;/*融资偿还额*/

    @SerializedName("d")
    public long  balanceValue;/*融资余额*/

    @SerializedName("e")
    public long  sellShares;/*融券卖出量*/

    @SerializedName("f")
    public long  repayShares;/*融券偿还量*/

    @SerializedName("g")
    public long balanceSharesValue; /*融券余额*/


    //有融资融券的股票的 secId 集
    public static final Set<Long> financingSecSet = Sets.newHashSet();

}
