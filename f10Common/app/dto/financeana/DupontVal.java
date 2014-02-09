package dto.financeana;

import com.google.gson.annotations.SerializedName;
import dto.BondSec;

import java.util.Date;

/**
 * 杜邦分析的数据
 * 计算值都使用的是计算公式的, 不是从数据库的字段提取的
 * User: wenzhihong
 * Date: 12-10-29
 * Time: 上午9:36
 */
public class DupontVal {
    public Long institutionId; //股票代码

    @SerializedName("rdate")
    public Date reportDate; //报告时间

    @SerializedName("mbc")
    public double mainBusinessCost; //主营业务成本

    @SerializedName("sc")
    public double sellingCost; //销售费用

    @SerializedName("ac")
    public double adminCost; //管理费用

    @SerializedName("fc")
    public double financialCost; //财务费用

    @SerializedName("otc")
    public double otherCost; //其它成本

    @SerializedName("si")
    public double salesIncome; //销售收入

    @SerializedName("oi")
    public double otherIncome; //其他利润

    @SerializedName("it")
    public double incomeTax;//所得税

    @SerializedName("la")
    public double livedAssets;//长期资产

    public double lev;//资产负债率

    @SerializedName("csec")
    public double cashSecurities; //现金有价证券

    @SerializedName("are")
    public double accountsReceivable;//应收账款

    @SerializedName("inve")
    public double inventory;//存货

    @SerializedName("oca")
    public double otherCurrentAssets;//其他流动资产

    @SerializedName("ftas")
    public double financeTotalAssert; //资产总额(金融类的要用)

    //权益乘数
    public double getEm(){
        return 1.0 / (1 - lev);
    }

    //全部成本
    public double getAllCost() {
        return mainBusinessCost + sellingCost + adminCost + financialCost + otherCost;
    }

    //净利润
    public double getNetProfit() {
        return salesIncome - getAllCost() + otherIncome - incomeTax;
    }

    //销售净利率
    public double getNetProfitMargain() {
        return salesIncome == 0 ? 0 : getNetProfit() / salesIncome;
    }

    //总资产净利率
    public double getJroa(BondSec sec) {
        return getNetProfitMargain() * getAssetTurnoverRatio(sec);
    }

    //净资产收益率
    public double getRoe(BondSec sec) {
        return getJroa(sec) * ( 1.0 / (1.0 - lev) );
    }

    //流动资产
    public double getFloatingAssets() {
        return cashSecurities + accountsReceivable + inventory + otherCurrentAssets;
    }

    //资产总额
    public double getTotalAssets(BondSec sec) {
        if (sec.isFinancial()) {
            return financeTotalAssert;
        } else {
            return livedAssets + getFloatingAssets();
        }
    }

    //总资产周转率
    public double getAssetTurnoverRatio(BondSec sec ) {
        return getTotalAssets(sec) == 0 ? 0 :  salesIncome / getTotalAssets(sec);
    }
}
