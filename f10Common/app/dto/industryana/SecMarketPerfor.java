package dto.industryana;

import com.google.common.collect.Lists;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * 市场表现
 * User: wenzhihong
 * Date: 12-12-14
 * Time: 上午10:07
 */
public class SecMarketPerfor {
    @SerializedName("a")
    public double return3Month1; /*近三个月累计涨跌幅*/

    @SerializedName("b")
    public double maxReturnDaily1; //最大回报值

    @SerializedName("c")
    public double minReturnDaily1; //最小回报值

    @SerializedName("d")
    public List<Item> details = Lists.newArrayListWithCapacity(93);

    public void addItem(Item i){
        details.add(i);
    }

    //每一项值
    public static class Item{
        @SerializedName("e")
        public Date tradingDate; //交易时间

        @SerializedName("f")
        public double returnDaily1;//回报率
    }
}
