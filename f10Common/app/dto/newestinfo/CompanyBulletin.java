package dto.newestinfo;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * 公司公告. 在Redis里是用list结构进行存储的
 * User: wenzhihong
 * Date: 12-9-20
 * Time: 下午1:45
 */
public class CompanyBulletin {

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
