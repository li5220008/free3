package dto.majormatter;

import com.google.gson.annotations.SerializedName;

/**
 * 重大事项   对外担保
 * User: panzhiwei
 * Date: 12-9-19
 * Time: 下午2:55
 */
public class Guarantee {
    @SerializedName("a")
    public String declareDate;/*公告日期*/

    @SerializedName("b")
    public String sdate;/*担保起始日*/

    @SerializedName("c")
    public String edate;/*担保终止日*/

    @SerializedName("d")
    public Double amount;/*担保金额*/

    @SerializedName("e")
    public String method;/*担保方式*/

    @SerializedName("f")
    public String guarantor; /*担保方*/

    @SerializedName("g")
    public String guarantee; /*被担保人*/
}

