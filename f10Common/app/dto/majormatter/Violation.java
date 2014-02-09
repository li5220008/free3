package dto.majormatter;

import com.google.gson.annotations.SerializedName;

/**
 * 违规处理
 * User: wenzhihong
 * Date: 12-12-17
 * Time: 下午12:09
 */
public class Violation {
    @SerializedName("a")
    public String declareDate;/*公告日期*/

    @SerializedName("b")
    public String  violationType; /*违规类型*/

    @SerializedName("c")
    public String  supervisor; /*处理单位*/

    @SerializedName("d")
    public String  disposalDate; /*处理决定文件日期*/

    @SerializedName("e")
    public String  processObject; /*处理对象*/
}
