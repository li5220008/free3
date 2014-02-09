package dto.relatedstock;

import com.google.gson.annotations.SerializedName;
import dto.BondSec;

/**
 * 同股东持股
 * User: panzhiwei
 * Date: 12-9-20
 * Time: 上午9:26
 */
public class SameShareHolder {
    @SerializedName("a")
    public String scode;/*股票代码*/

    @SerializedName("b")
    public String holderName;/*股东名称*/

    @SerializedName("c")
    public long  holderId;/*股东id*/

    @SerializedName("d")
    public long  holdNum; /*持股数量*/

    public String secName(){
        return BondSec.secMap.containsKey(scode) ? BondSec.secMap.get(scode).name : "";
    }
}
