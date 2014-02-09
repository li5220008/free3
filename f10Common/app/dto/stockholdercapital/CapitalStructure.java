package dto.stockholdercapital;

import com.google.gson.annotations.SerializedName;

/**
 * 股本结构
 * User: wenzhihong
 * Date: 12-9-21
 * Time: 上午10:36
 */
public class CapitalStructure {
    //未流通股份数
    @SerializedName("a")
    public Long nonTradableShare;

    public double nonTradableShareRate() {
        if (total == null || total == 0 || nonTradableShare == null) {
            return 0;
        } else {
            return nonTradableShare * 1.0 / total * 100;
        }
    }

    //流通受限股份数
    @SerializedName("b")
    public Long lockSharesTotal;

    public double lockSharesTotalRate() {
        if (total == null || total == 0 || lockSharesTotal == null) {
            return 0;
        } else {
            return lockSharesTotal * 1.0 / total * 100;
        }
    }

    //已流通股份合计
    @SerializedName("c")
    public Long tradeSharesTotal;

    public double tradeSharesTotalRate() {
        if (total == null || total == 0 || tradeSharesTotal == null) {
            return 0;
        } else {
            return tradeSharesTotal * 1.0 / total * 100;
        }
    }

    //总股本
    @SerializedName("d")
    public Long total;

    //已上市流通A股数
    @SerializedName("e")
    public Long tradableSharesA;

    public double tradableSharesARate() {
        if (tradeSharesTotal == null || tradeSharesTotal == 0 || tradableSharesA == null) {
            return 0;
        } else {
            return tradableSharesA * 1.0 / tradeSharesTotal * 100;
        }
    }

    //已上市流通B股数
    @SerializedName("f")
    public Long tradableSharesB;

    public double tradableSharesBRate() {
        if (tradeSharesTotal == null || tradeSharesTotal == 0 || tradableSharesB == null) {
            return 0;
        } else {
            return tradableSharesB * 1.0 / tradeSharesTotal * 100;
        }
    }

    //已上市流通H股数
    @SerializedName("g")
    public Long tradableSharesH;

    public double tradableSharesHRate() {
        if (tradeSharesTotal == null || tradeSharesTotal == 0 || tradableSharesH == null) {
            return 0;
        } else {
            return tradableSharesH * 1.0 / tradeSharesTotal * 100;
        }
    }

    //其他已流通股份
    @SerializedName("h")
    public Long otherTradableShares;

    public double otherTradableSharesRate() {
        if (tradeSharesTotal == null || tradeSharesTotal == 0 || otherTradableShares == null) {
            return 0;
        } else {
            return otherTradableShares * 1.0 / tradeSharesTotal * 100;
        }
    }

    //流通A股占比
    public double tradableSharesARateForPic() {
        double trA = tradableSharesA == null ? 0 : tradableSharesA;
        double lockT = lockSharesTotal == null ? 0 : lockSharesTotal;
        if (trA + lockT != 0) {
            return trA / (trA + lockT) * 100;
        } else {
            return 0.0;
        }

    }

    //股改限售股份占比
    public double lockSharesTotalRateForPic() {
        double trA = tradableSharesA == null ? 0 : tradableSharesA;
        double lockT = lockSharesTotal == null ? 0 : lockSharesTotal;
        if (trA + lockT != 0) {
            return lockT / (trA + lockT) * 100;
        } else {
            return 0.0;
        }
    }
}
