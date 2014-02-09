package dto.companyinfo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * 公司基本信息
 * User: wenzhihong
 * Date: 12-12-7
 * Time: 下午12:00
 */
public class InstitutionInfoDto {
    @SerializedName("a")
    public String  fullName; /*名称*/

    @SerializedName("b")
    public String  regAddress; /*公司地址*/

    @SerializedName("c")
    public String  zipCode;     /*邮政编码*/

    @SerializedName("d")
    public String  secretaryTel; /*公司电话*/

    @SerializedName("e")
    public String  secretaryFax; /*公司传真*/

    @SerializedName("f")
    public String  email; /*电子邮箱*/

    @SerializedName("g")
    public String  webSite; /*公司网址*/

    @SerializedName("h")
    public String  businessScope; /*经营范围*/

    @SerializedName("i")
    public String  mainBusiness; /*主营业务*/

    @SerializedName("j")
    public String  introduce; /*公司介绍*/

    @SerializedName("k")
    public String  lawPerson; /*法人代表*/

    @SerializedName("l")
    public String  secretary; /*联系人(董秘)*/

    @SerializedName("m")
    public String province; /*所属地区*/

    //webSite可能是多个用；或者,分隔的网址，页面上分开显示
    public List<String> fetchWebSiteList(){
        List<String> webSiteList = new ArrayList<String>();
        if(webSite==null){
            return webSiteList;
        }
        if(webSite.indexOf(";")!=-1){
            for(String webSites : webSite.split(";")){
                webSites = webSites.trim();
                if(!webSites.startsWith("http")){
                    webSiteList.add("http://"+webSites);
                }else {
                    webSiteList.add(webSites);
                }
            }
        }else{
            for(String webSites : webSite.split(",")){
                webSites = webSites.trim();
                if(!webSites.startsWith("http")){
                    webSiteList.add("http://"+webSites);
                }else {
                    webSiteList.add(webSites);
                }
            }
        }
        return webSiteList;
    }
}
