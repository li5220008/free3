package dto.financeana;

import util.CommonUtils;
import util.FieldOut;

/**
 * User: wenzhihong
 * Date: 12-12-24
 * Time: 下午1:47
 */
public class OperateIncomeCosts {
    public static final String fieldOutJson = CommonUtils.fieldOutJson(OperateIncomeCosts.class);

    public String enddateStr;

    @FieldOut(cnName = "项目", order = 1)
    public String item;/*项目*/

    @FieldOut(cnName = "类别", order = 2)
    public String category;/*类别*/

    public String category2Cn() {
        if (category == null) {
            return "--";
        }
        category = category.trim();
        if ("1".equals(category)) {
            return "主营业务收入";
        }else if ("2".equals(category)) {
            return "其他业务收入";
        }else if ("3".equals(category)) {
            return "营业收入";
        }else{
            return category;
        }
    }

    @FieldOut(cnName = "分布标准", order = 3)
    public String distributionStandard;/*分布标准*/

    public String distributionStandard2Cn() {
        if (distributionStandard == null) {
            return "--";
        }
        distributionStandard = distributionStandard.trim();
        if ("1".equals(distributionStandard)) {
            return "按行业或业务分布";
        }else if ("2".equals(distributionStandard)) {
            return "按地区分布";
        }else if ("3".equals(distributionStandard)) {
            return "按产品分布";
        }else {
            return distributionStandard;
        }

    }

    @FieldOut(cnName = "币种", order = 4)
    public String currency;/*币种*/

    @FieldOut(cnName = "本期收入金额", order = 5)
    public double earnings;/*本期收入金额*/

    @FieldOut(cnName = "本期成本金额", order = 6)
    public double costs;/*本期成本金额*/

    @FieldOut(cnName = "本期毛利率", order = 7)
    public double grossMarginRate;/*本期毛利率*/

    @FieldOut(cnName = "上期收入金额", order = 8)
    public double previousEarnings;/*上期收入金额*/

    @FieldOut(cnName = "上期成本金额", order = 9)
    public double previousCosts;/*上期成本金额*/

    @FieldOut(cnName = "上期毛利率", order = 10)
    public double previousGrossMarginRate;/*上期毛利率*/
}
