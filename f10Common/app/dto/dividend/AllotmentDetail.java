package dto.dividend;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * 配股明细
 * User: wenzhihong
 * Date: 12-12-13
 * Time: 下午5:33
 */
public class AllotmentDetail {
    @SerializedName("a")
    public Date declareDate; /*配股说明书公告日期*/

    @SerializedName("b")
    public Double placingRatio;/*配股比例*/

    @SerializedName("c")
    public Double price;	/*配售价格*/

    @SerializedName("d")
    public Date registerDate;/*股权登记日*/

    @SerializedName("e")
    public Date exRightDate;/*除权基准日*/

    @SerializedName("f")
    public Date listedDate; /*配股上市流通日*/

    @SerializedName("g")
    public Double raiseFund;	/*实际募集资金净额*/

    @SerializedName("h")
    public String saleMan; /*承销商*/
}
