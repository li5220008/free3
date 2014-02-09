package dto.financeana;

/**
 * User: wenzhihong
 * Date: 12-12-21
 * Time: 下午2:34
 */
public class PerShareItem {
    public String enddateStr;

    public double eps;	/*基本每股收益*/
    public double dilutedEPS; /*稀释每股收益*/
    public double ePSA;/*每股收益-期末股本摊薄*/
    public double ePSB;/*每股收益-最新股本摊薄*/
    public double ePSTTMA;/*每股收益TTM-期末股本摊薄*/
    public double ePSTTMB;/*每股收益TTM-最新股本摊薄*/
    public double retainedEarningPerShare;/*每股留存收益*/
    public double eBITPerShare;/*息税前每股收益*/
    public double totalRevenuePerShare;/*每股营业总收入*/
    public double revenuePerShare;/*每股营业收入*/
    public double operatingProfitPerShare;/*每股营业利润*/
    public double nAV;/*每股净资产*/
    public double operatingNCFPerShare;/*每股经营活动产生的现金流量*/
    public double capitalSurplusPerShare;/*每股资本公积*/
    public double surplusReservePerShare;/*每股盈余公积*/
    public double undistributedProfitPerShare;/*每股未分配利润*/
    public double companyNCFPerShareA;/*每股企业自由现金流量*/
}
