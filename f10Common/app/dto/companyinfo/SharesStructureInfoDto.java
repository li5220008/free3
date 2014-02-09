package dto.companyinfo;

import com.google.gson.annotations.SerializedName;

/**
 * 股本信息
 * User: wenzhihong
 * Date: 12-12-7
 * Time: 下午2:42
 */
public class SharesStructureInfoDto {
    @SerializedName("a")
    public Long totalStock; /*总股本*/

    @SerializedName("b")
    public Long tradeSharesTotal;/*流通股*/

    @SerializedName("c")
    public Long issueTotal; /*发行后总股本*/
}
