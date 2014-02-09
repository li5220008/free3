package dto.dividend;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * 分红明细
 * User: wenzhihong
 * Date: 12-12-13
 * Time: 下午4:13
 */
public class CashBonusDetail {
    @SerializedName("a")
    public Date  declareDate; /*公告日期*/

    @SerializedName("b")
    public Double  cashBonus /*分红*/;

    @SerializedName("c")
    public Double  send; /*送股*/

    @SerializedName("d")
    public Double  turn; /*转股*/

    @SerializedName("e")
    public Date  recordDate; /*登记日*/

    @SerializedName("f")
    public Double  dividendDistri; /*派现额度*/

    @SerializedName("g")
    public Date exDividendDate;/*除权日*/
}
