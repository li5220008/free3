package dto.dividend;

import com.google.gson.annotations.SerializedName;

/**
 * 融资项总和
 * User: wenzhihong
 * Date: 12-12-13
 * Time: 上午9:07
 */
public class RaiseFundOverall {
    @SerializedName("a")
    public long secId;

    //配股
    @SerializedName("b")
    public double allotmentSum = 0;
    @SerializedName("c")
    public int allotmentCount = 0;

    //增发1
    @SerializedName("d")
    public double addIssuing1Sum = 0;
    @SerializedName("e")
    public int addIssuing1Count = 0;

    //增发2
    @SerializedName("f")
    public double addIssuing2Sum = 0;
    @SerializedName("g")
    public int addIssuing2Count = 0;

    //新股发行
    @SerializedName("h")
    public double firstIssuingSum = 0;
    @SerializedName("i")
    public int firstIssuingCount = 1; //只有一次,是常量

    //现金分红
    @SerializedName("j")
    public double cashBonusSum = 0;
    @SerializedName("k")
    public int cashBonusCount = 0;

    //比率
    @SerializedName("l")
    public double rate;

    //比率在市场的排名
    @SerializedName("m")
    public int rateRanking;

    //送股次数
    @SerializedName("n")
    public int sendCount = 0;

    //增发总额
    public double addIssuingSum(){
        return addIssuing1Sum + addIssuing2Sum;
    }

    //增发次数
    public int addIssuingCount(){
        return addIssuing1Count + addIssuing2Count;
    }

    //融资总额
    public double fundRaisingTotal(){
        return allotmentSum + addIssuing1Sum + addIssuing2Sum + firstIssuingSum;
    }

    //计算 现金分红 / 融资总额 比率
    public double getRate(){
        rate = fundRaisingTotal() == 0 ? 0 : cashBonusSum / fundRaisingTotal();
        return rate;
    }
}
