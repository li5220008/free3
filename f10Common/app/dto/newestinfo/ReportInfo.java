package dto.newestinfo;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * 公司研报信息
 * User: wenzhihong
 * Date: 12-10-18
 * Time: 下午2:16
 */
public class ReportInfo {
    //标号
    @SerializedName("a")
    public String guid;

    //标题
    @SerializedName("b")
    public String title;

    //发布时间
    @SerializedName("c")
    public Date pubDate;

    //附件地址
    @SerializedName("d")
    public String attachUrl;

    @SerializedName("e")
    public String fileType;
}
