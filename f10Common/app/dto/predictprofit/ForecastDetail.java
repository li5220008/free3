package dto.predictprofit;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * 预测明细
 * User: wenzhihong
 * Date: 12-12-20
 * Time: 上午10:19
 */
public class ForecastDetail {
    @SerializedName("a")
    public long reportId; /*研报id*/

    @SerializedName("b")
    public Date reportDate; /*研报时间*/

    @SerializedName("c")
    public String orgName; /*机构名称*/

    @SerializedName("d")
    public int forecastYear; /*预测年度*/

    @SerializedName("e")
    public double price; /*价格*/

    @SerializedName("f")
    public String analyst; /*研究员*/

    @SerializedName("g")
    public String rating; /*标准化评级*/
}
