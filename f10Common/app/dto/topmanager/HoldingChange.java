package dto.topmanager;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * 高官持股变动
 * User: panzhiwei
 * Date: 12-9-17
 * Time: 上午10:33
 */
public class HoldingChange {
    @SerializedName("a")
    public Date  changeDate; /*变动日期*/

    @SerializedName("b")
    public String shareholderName; /*变动人*/

    @SerializedName("c")
    public long  changeNum; /*变动数量*/

    @SerializedName("d")
    public long  afterNum; /*结存数量*/

    @SerializedName("e")
    public Double avgPrice;/*交易均价*/

    @SerializedName("f")
    public String mangeName; /*董监高名称*/

    @SerializedName("g")
    public String relationship; /*与高管关系*/

    @SerializedName("h")
    public String changeMethod; /*股份变动途径*/
}
