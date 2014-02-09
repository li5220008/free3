package dto.stockholdercapital;

import com.google.common.collect.Maps;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.Map;

/**
 * 机构分类持股(也就是按分类进行合计的)
 * User: wenzhihong
 * Date: 12-12-8
 * Time: 下午6:02
 */
public class OrgGroupSumHolder {
    @SerializedName("a")
    public String typeId;

    @SerializedName("b")
    public Date endDate;

    @SerializedName("c")
    public double sumRatio = 0.0; //合计比例

    @SerializedName("d")
    public int orgCount = 0; //机构个数

    /*
      *     定义股东机构类型常量
      *     根据需求：
      *     基金公司（柱状图）= fundCompany+extra+socialSecurityFund
      *     其他机构(饼图) = otherOrgs+extra
     */
    public static final String[] qfii = {"P2201"};  //合格境外投资机构(QFII)
    public static final String[] bondCompany = {"P2203"}; //证券公司
    public static final String[] fundCompany = {"P2204"}; //基金公司
    public static final String[] insuranceCompany = {"P2205"}; //保险公司
    public static final String[] socialSecurityFund = {"P2244"}; //社保基金
    public static final String[] bondFinancing = {"P2245"}; //券商集合理财
    public static final String[] extra = {"P2241", "P2248"}; //证券投资基金+公益基金

    static final String[] otherOrgs = {"P2202", "P2206", "P2207", "P2208", "P2209", "P2210", "P2211", "P2212", "P2213",
        "P2239", "P2242", "P2243", "P2246", "P2247", "P2269"}; //其它机构

    public static final Map<String,String[]> typeGroupMap = Maps.newHashMap();
    public static final String typeGroup;
    static {
        typeGroupMap.put("qfii", qfii);
        typeGroupMap.put("bondCompany", bondCompany);
        typeGroupMap.put("fundCompany", fundCompany);
        typeGroupMap.put("insuranceCompany", insuranceCompany);
        typeGroupMap.put("socialSecurityFund", socialSecurityFund);
        typeGroupMap.put("bondFinancing", bondFinancing);
        typeGroupMap.put("otherOrgs", otherOrgs);
        typeGroupMap.put("extra", extra);

        StringBuilder sb = new StringBuilder();
        for (String[] valItem : typeGroupMap.values()) {
            for (String s : valItem) {
                sb.append(",'" + s + "'");
            }
        }
        typeGroup = sb.substring(1);
    }
}
