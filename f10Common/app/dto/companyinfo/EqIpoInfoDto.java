package dto.companyinfo;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * ipo信息
 * User: wenzhihong
 * Date: 12-12-7
 * Time: 下午1:48
 */
public class EqIpoInfoDto {
    @SerializedName("a")
    public Date issueDate; /*发行日期*/

    @SerializedName("b")
    public Double  taxRate; /*所得税率*/

    @SerializedName("c")
    public Long  issueNumber; /*发行股数*/

    @SerializedName("d")
    public Double  pubPrice; /*发行价*/

    @SerializedName("e")
    public Date  listDate; /*上市日期*/

    @SerializedName("f")
    public Double  raiseNetFund; /*募集资金总计*/

    @SerializedName("g")
    public Double  issueExpenses; /*发行费用*/

    @SerializedName("h")
    public Double  parValue; /*股票面值*/

    @SerializedName("i")
    public String  issueMode; /*发行方式*/

    @SerializedName("j")
    public Double  pe4; /*发行市盈率*/

    @SerializedName("k")
    public Double  eps1; /*发行前每股收益*/

    @SerializedName("l")
    public Double  eps2; /*发行后每股收益*/

    @SerializedName("m")
    public Double  nav1; /*发行前每股净资产*/

    @SerializedName("n")
    public Double  nav2; /*发行后每股净资产*/

    @SerializedName("o")
    public String  currencyCode; /*币种*/

    @SerializedName("p")
    public Double forecastEps; /*年度预测每股收益*/

    @SerializedName("q")
    public Double firstDatePrice; /*首日价格*/
}
