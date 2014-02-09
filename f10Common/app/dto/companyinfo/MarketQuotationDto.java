package dto.companyinfo;

import com.google.gson.annotations.SerializedName;

/**
 * 市场行情
 * User: wenzhihong
 * Date: 12-12-7
 * Time: 下午3:18
 */
public class MarketQuotationDto {
    @SerializedName("w1")
    public double pe;

    @SerializedName("a")
    public double   preClosePrice; /*昨收价*/

    @SerializedName("b")
    public double  openPrice; /*开盘价*/

    @SerializedName("c")
    public double  closePrice;/*收盘价*/

    @SerializedName("d")
    public double  highPrice;/*最高价*/

    @SerializedName("e")
    public double  lowPrice;/*最低价*/

    @SerializedName("f")
    public double  volume; /*成交量*/

    @SerializedName("g")
    public double  amount; /*成效额*/

    @SerializedName("h")
    public double  avgPrice;/*均价*/

    @SerializedName("i")
    public double  schange; /*涨跌*/

    @SerializedName("j")
    public double  changeRatio; /*涨跌幅*/

    @SerializedName("k")
    public double  totalShare;/*总股本*/

    @SerializedName("l")
    public double  circulatedShare;/*流通股本*/

    @SerializedName("m")
    public double  turnoverRate1;/*换手率1*/

    @SerializedName("n")
    public double  turnoverRate2;/*换手率2*/

    @SerializedName("o")
    public double  marketValue;/*总市值*/

    @SerializedName("p")
    public double  circulatedMarketValue; /*流通市值*/

    @SerializedName("q")
    public double  amplitude; /*振幅*/

}
