package dto.predictprofit;

import com.google.gson.annotations.SerializedName;

/**
 * 评级变动
 * User: wenzhihong
 * Date: 12-12-21
 * Time: 上午10:38
 */
public class RatingChange {
    @SerializedName("a")
    public String orgName; /*研究机构*/

    @SerializedName("b")
    public String reportDate;/*研报日期*/

    @SerializedName("c")
    public String ratingChange;/*评级变动*/

    @SerializedName("d")
    public String rating; /*评级*/
}
