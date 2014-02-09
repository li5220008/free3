package dto.stockholdercapital;

import com.google.gson.annotations.SerializedName;

/**
 * 限售解禁
 * User: liuhongjiang
 * Date: 12-9-21
 * Time: 上午10:33
 */
public class LimitedAndLift {
    //解禁时间, (yyyy-MM-dd 格式)
    @SerializedName("a")
    public String changeDate;

    //解禁数量 (本次流通股合计-上次流通股合计)
    @SerializedName("b")
    public long liftNum;

    //总股本
    @SerializedName("c")
    public long total;

    //流通股合计
    @SerializedName("d")
    public long tradeSharesTotal;

    //解禁原因
    @SerializedName("e")
    public String changeReason;

    //变动原因id
    @SerializedName("f")
    public String changeReasonId;

    public double liftTradeScale(){
        return tradeSharesTotal == 0 ? 0.0 : liftNum * 1.0 / tradeSharesTotal * 100;
    }

    public double liftTotalScale(){
        return total == 0 ? 0.0 : liftNum * 1.0 / total * 100;
    }
}
