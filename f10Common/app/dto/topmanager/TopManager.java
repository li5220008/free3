package dto.topmanager;

import com.google.gson.annotations.SerializedName;

/**
 * 高管人员   离职变动
 * User: panzhiwei
 * Date: 12-9-14
 * Time: 上午9:46
 */
public class TopManager {
    //以下定义高管的数据库编码
    //高管
    public static final String executives_code = "P353";
    //董事会
    public static final String board_code = "P351";
    //监事会
    public static final String aufsichtsrat_code = "P352";

    //职位类型
    @SerializedName("a")
    public String positionType;

    //姓名
    @SerializedName("b")
    public String name;

    //出生年份
    @SerializedName("c")
    public int birthYear;

    //性别
    @SerializedName("d")
    public String sex;

    //学历
    @SerializedName("e")
    public String degree;

    //任职日期
    @SerializedName("f")
    public String entrantDate;

    public String getEntrantDate() {
        if (entrantDate == null) {
            return "未知";
        } else if (entrantDate.equals("1000-12-31")) {
            return "未知";
        } else {
            return entrantDate;
        }
    }

    //离职日期
    @SerializedName("g")
    public String leaveDate;

    public String getLeaveDate() {
        if (leaveDate == null) {
            return "--";
        } else if (leaveDate.equals("9999-12-31")) {
            return "--";
        } else {
            return leaveDate;
        }
    }

    //职位
    @SerializedName("h")
    public String position;

    //职称
    @SerializedName("i")
    public String professionalTitle;

    //简历
    @SerializedName("j")
    public String resume;

}
