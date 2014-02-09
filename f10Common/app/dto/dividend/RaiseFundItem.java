package dto.dividend;

import com.google.gson.annotations.SerializedName;

/**
 * 融资项
 * User: wenzhihong
 * Date: 12-12-12
 * Time: 下午6:14
 */
public class RaiseFundItem {
    @SerializedName("a")
    public double raiseFundSum; /*融资金额*/

    @SerializedName("b")
    public int raiseFundCount;  /*融资次数*/
}
