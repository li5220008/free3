package dto.industryana;

import com.google.gson.annotations.SerializedName;
import dto.BondSec;

/**
 * 公司规模
 * User: wenzhihong
 */
public class CompanyScale {
    //排名
    @SerializedName("a")
    public int rank;

    //代码
    @SerializedName("b")
    public long institutionId;

    //总市值
    @SerializedName("c")
    public double totalValue;

    public String secCode(){
        return BondSec.fetchScodeByInstitutionId(institutionId, BondSec.Type_A);
    }

    public String secName(){
        BondSec sec = BondSec.fetchSecByInstitutionId(institutionId, BondSec.Type_A);
        return sec == null ? "" : sec.name;
    }
}
