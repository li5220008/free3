package dto.newestinfo;

import com.google.gson.annotations.SerializedName;

/**
 * 研报统计(综合评级)
 * User: wenzhihong
 * Date: 12-11-26
 * Time: 下午5:42
 */
public class ReportRatingStatisticDto {
    @SerializedName("a")
    public String statisticDate; //最新统计日期

    //买入
    @SerializedName("c")
    public int buy = 0;

    //增持
    @SerializedName("e")
    public int outperform = 0;

    //中性
    @SerializedName("g")
    public int neutral = 0;

    //减持
    @SerializedName("i")
    public int underperform = 0;

    //卖出
    @SerializedName("k")
    public int sell = 0;

    //评级数(总共有多少篇)
    @SerializedName("m")
    public int rateSum = 0;

    //最新综合评级
    @SerializedName("o")
    public String rateResult;

    public int orgCount(){
        return (buy + underperform + neutral + sell + outperform);
    }

    //计算最新综合评级(数字)
    public float calcLastRateNumber(){
        int count = (buy + underperform + neutral + sell + outperform);
        return count == 0 ? 0 : (buy * 5 + underperform * 2 + neutral * 3 + outperform * 4 + sell * 1) / (count*1.0f);
    }

    //计算最新综合评级(文字)
    public String calcLastRateResult(){
        double rate = calcLastRateNumber();
        if (rate <= 0) {
            return "未知";
        } else if (rate > 0 && rate <= 1) {
            return "卖出";
        } else if (rate > 1 && rate <= 2) {
            return "减持";
        } else if (rate > 2 && rate <= 3) {
            return "中性";
        } else if (rate > 3 && rate <= 4) {
            return "增持";
        } else {
            return "买入";
        }
    }
}
