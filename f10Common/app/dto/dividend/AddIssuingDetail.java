package dto.dividend;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * 增发明细
 * User: wenzhihong
 * Date: 12-12-13
 * Time: 下午5:22
 */
public class AddIssuingDetail {
    @SerializedName("a")
    public Date declareDate;/*公告日*/

    @SerializedName("b")
    public String  issueObject;/*增发对象*/

    @SerializedName("c")
    public String issueMode; /*发行方式*/

    @SerializedName("d")
    public Double price;/*价格*/

    @SerializedName("e")
    public Long  issueShares;/*实际发行数量*/

    @SerializedName("f")
    public Double  raiseNetFund;/*实际募集净额*/

    @SerializedName("g")
    public Date  listedDate;/*增发上市日*/

    @SerializedName("h")
    public Long  totNumShares; /*增发后总股本*/
}
