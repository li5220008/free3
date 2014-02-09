package dto.relatedstock;

import com.google.gson.annotations.SerializedName;

/**
 * 10大股东的简要. 只包含持股人id和名称
 * User: wenzhihong
 * Date: 12-12-17
 * Time: 下午3:51
 */
public class HolderItem {
    @SerializedName("a")
    public long id;

    @SerializedName("b")
    public String name;
}
