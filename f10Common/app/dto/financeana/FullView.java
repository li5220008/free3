package dto.financeana;

import com.google.gson.annotations.SerializedName;

/**
 * 财务全景合并
 * User: wenzhihong
 * Date: 12-12-22
 * Time: 上午11:38
 */
public class FullView {
    //短期偿债能力
    @SerializedName("a")
    public String shortDebtRepaymentDate;
    @SerializedName("as")
    public double shortDebtRepaymentStep;

    //长期偿债能力
    @SerializedName("b")
    public String longDebtRepaymentDate;
    @SerializedName("bs")
    public double longDebtRepaymentStep;

    //经营能力
    @SerializedName("c")
    public String  operateCapacityDate;
    @SerializedName("cs")
    public double operateCapacityStep;

    //盈利能力
    @SerializedName("d")
    public String earnPowerCapacityDate;
    @SerializedName("ds")
    public double earnPowerCapacityStep;

    //投资收益
    @SerializedName("e")
    public String roiCapacityDate;
    @SerializedName("es")
    public double roiCapacityStep;

    //成长性
    @SerializedName("f")
    public String developmentCapacityDate;
    @SerializedName("fs")
    public double developmentCapacityStep;


    //综合能力
    public double comprehensiveCapacity(){
        return (shortDebtRepaymentStep + longDebtRepaymentStep + operateCapacityStep + earnPowerCapacityStep + roiCapacityStep + developmentCapacityStep) / 6;
    }
}
