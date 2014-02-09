package dto.stockholdercapital;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * 股东持股
 * User: wenzhihong
 * Date: 12-12-8
 * Time: 下午2:45
 */
public class StockHolderDto {

    @SerializedName("a")
    public long holderId; /*股东id*/

    @SerializedName("b")
    public String  holderName; /*股东名称*/

    @SerializedName("c")
    public Date endDate; /*截止日期*/

    @SerializedName("d")
    public String  holderType; /*持股类型*/

    @SerializedName("e")
    public Long  holdNum; /*持股数量*/

    @SerializedName("f")
    public Double  rate; /*比率*/

    @SerializedName("g")
    public Long change; /*较上期变动*/

    //以下两个字段为冗余的
    @SerializedName("z")
    public Date firstDate;
    @SerializedName("x")
    public Date secondDate;
}
