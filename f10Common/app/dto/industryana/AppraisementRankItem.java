package dto.industryana;

import com.google.gson.annotations.SerializedName;
import dto.BondSec;

/**
 * 估值水平 (市盈率) 排名
 * User: wenzhihong
 * Date: 12-12-15
 * Time: 上午10:36
 */
public class AppraisementRankItem {
    //排名
    @SerializedName("a")
    public int rank;

    //代码
    @SerializedName("b")
    public long secId;

    //总市值
    @SerializedName("c")
    public double pe1a;

    public String secCode(){
        return BondSec.secIdToCodeMap.get(secId);
    }

    public String secName(){
        String secCode = BondSec.secIdToCodeMap.get(secId);
        if(secCode == null || BondSec.secMap.get(secCode) == null){
            return null;
        }

        return BondSec.secMap.get(secCode).name;
    }
}
