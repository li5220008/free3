package dto.newestinfo;

import com.google.gson.annotations.SerializedName;

/**
 * 大事提醒
 * User: wenzhihong
 * Date: 12-11-23
 * Time: 上午11:16
 */
public class GreatInventRemind {
    //股票ID
    public long secId;
    //事件种类
    @SerializedName("a")
    public String eventType;
    //提示内容
    @SerializedName("b")
    public String content;
    //公告日期
    @SerializedName("c")
    public String declareDate;
    //股票代码

    public String symbol;
    //代码简称
    public String shortName;



    @Override
    public String toString() {
        return "GreatInventRemind{" +
                "eventType='" + eventType + '\'' +
                ", content='" + content + '\'' +
                ", declareDate='" + declareDate + '\'' +
                '}';
    }
}
