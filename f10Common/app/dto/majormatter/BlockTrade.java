package dto.majormatter;

import com.google.gson.annotations.SerializedName;

/**
 * 大宗交易
 * User: panzhiwei
 * Date: 12-9-19
 * Time: 下午4:58
 */
public class BlockTrade {
    @SerializedName("a")
    public String tradeDate;/*交易日期*/

    @SerializedName("b")
    public double  price;/*价格*/

    @SerializedName("c")
    public long  volume;/*成交量*/

    @SerializedName("d")
    public double amout;/*成效额*/

    @SerializedName("e")
    public String  buyer;/*买方营业部名称*/

    @SerializedName("f")
    public String  seller;/*卖方营业部名称*/
}
