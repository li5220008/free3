package dto.predictprofit;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * 按研报来还原明细项
 * User: wenzhihong
 * Date: 12-12-20
 * Time: 下午4:56
 */
public class DetailByReport {
    @SerializedName("a")
    public long reportId; /*研报id*/

    @SerializedName("b")
    public Date reportDate; /*研报时间*/

    @SerializedName("c")
    public String orgName; /*机构名称*/

    @SerializedName("d")
    public String analyst; /*研究员*/

    @SerializedName("e")
    public String rating; /*标准化评级*/

    @SerializedName("f")
    public double fprice1; /*预测价格1*/

    @SerializedName("g")
    public double fprice2; /*预测价格2*/

    @SerializedName("h")
    public double fprice3; /*预测价格3*/

}
