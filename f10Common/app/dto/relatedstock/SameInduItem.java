package dto.relatedstock;

import com.google.gson.annotations.SerializedName;
import dto.BondSec;

/**
 * 同行业各股eps
 * User: wenzhihong
 * Date: 12-12-17
 * Time: 下午3:33
 */
public class SameInduItem {
    @SerializedName("a")
    public double eps;

    @SerializedName("b")
    public String scode;

    public String secName() {
        return BondSec.secMap.containsKey(scode) ? BondSec.secMap.get(scode).name : "";
    }
}
