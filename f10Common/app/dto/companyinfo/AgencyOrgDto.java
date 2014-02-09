package dto.companyinfo;

import com.google.gson.annotations.SerializedName;

/**
 * 承销商, 代理机构
 * User: wenzhihong
 * Date: 12-12-7
 * Time: 下午6:30
 */
public class AgencyOrgDto {
    @SerializedName("a")
    public String referrer; /*推荐人*/

    @SerializedName("b")
    public String mainSaleMan; /*主承销商*/

    @SerializedName("c")
    public String secondSaleMan; /*推荐人*/
}
