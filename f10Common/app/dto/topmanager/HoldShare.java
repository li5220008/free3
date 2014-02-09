package dto.topmanager;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * 高管持股
 * User: panzhiwei
 * Date: 12-11-2
 * Time: 上午9:25
 */
public class HoldShare {
    //时间
    @SerializedName("a")
    public Date endDate;

    //持股人
    @SerializedName("b")
    public String holdName;

    //持股数
    @SerializedName("c")
    public long holdNum;
}
