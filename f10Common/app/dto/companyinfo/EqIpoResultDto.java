package dto.companyinfo;

import com.google.gson.annotations.SerializedName;

/**
 * ipoResult
 * User: wenzhihong
 * Date: 12-12-7
 * Time: 下午2:03
 */
public class EqIpoResultDto {
    @SerializedName("a")
    public String  priceScope;/*发行询价区间*/

    @SerializedName("b")
    public Double  successRate;/*中签率1*/

    @SerializedName("c")
    public Long  listedShares; /*首日上市股数*/

    @SerializedName("d")
    public Long purchaseHouseholders; /*申购股数*/
}
