package dto.newestinfo;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

/**
 * 板块dto
 * User: wenzhihong
 * Date: 12-12-6
 * Time: 下午5:53
 */
public class PlateDto {
    @SerializedName("a")
    public BigDecimal plateId; //板块id

    @SerializedName("b")
    public String plateTitle; //板块名称

}
