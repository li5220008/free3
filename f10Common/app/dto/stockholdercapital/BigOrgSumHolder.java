package dto.stockholdercapital;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * 大的机构类型持股合计
 * User: wenzhihong
 * Date: 12-12-10
 * Time: 下午1:58
 */
public class BigOrgSumHolder {
    @SerializedName("a")
    public String name; //大的类型名称

    @SerializedName("b")
    public Date endDate;

    @SerializedName("c")
    public double sumRatio = 0.0; //合计比例

    @SerializedName("d")
    public int orgCount = 0; //机构个数
}
