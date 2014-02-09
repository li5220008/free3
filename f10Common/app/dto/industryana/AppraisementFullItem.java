package dto.industryana;

import com.google.gson.annotations.SerializedName;
import dto.BondSec;

/**
 * 估值水平 全部项
 * User: wenzhihong
 * Date: 12-12-29
 * Time: 上午9:39
 */
public class AppraisementFullItem {
    //注意:这里的属性要是修改的话, 请同步修改 InduCompareAppraisementJob.middleVal 方法. 那里是用字面量反射排序
    @SerializedName("z")
    public long secId;

    @SerializedName("a")
    public int lastYear; /*最新的年度*/

    @SerializedName("b")
    public double pe1a;/*市盈率(11A)*/

    @SerializedName("c")
    public double pe1ttm;/*市盈率(TTM)*/

    @SerializedName("d")
    public double psa;   /*市销率(11A)*/

    @SerializedName("e")
    public double psttm;/*市销率(TTM)*/

    @SerializedName("f")
    public double pbva; /*市净率(11A)*/

    @SerializedName("g")
    public double pbCur; /*市净率(现值)*/

    @SerializedName("h")
    public double pcfa;/*市现率(11A)*/

    @SerializedName("i")
    public double pcfttm;/*市现率(TTM)*/

    @SerializedName("j")
    public double evtoebitdattm;/*EV/EBITDA(TTM)*/

    @SerializedName("k")
    public double evtoebitda; /*EV/EBITDA(11A)*/

    @SerializedName("l")
    public double marketValue; /*总市值*/

    @SerializedName("m")
    public int rank; /*排名*/

    public double closePrice; /*收盘价*/

    //取后两位
    public String yearStr(){
       String s =  String.valueOf(100000 + lastYear);
       return s.substring(s.length()-2);
    }

    public String sname(){
        BondSec sec = BondSec.fetchSecBySecId(secId);
        return  sec == null ? "" : sec.name;
    }

    public String scode(){
        BondSec sec = BondSec.fetchSecBySecId(secId);
        return sec == null ? "" : sec.code;
    }

}
