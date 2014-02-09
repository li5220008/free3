package dto.financeana;

import util.CommonUtils;
import util.FieldOut;

/**
 * User: wenzhihong
 * Date: 12-12-24
 * Time: 下午1:50
 */
public class FinanceCosts {
    public static final String fieldOutJson = CommonUtils.fieldOutJson(FinanceCosts.class);

    public String enddateStr;

    @FieldOut(cnName = "项目", order = 1)
    public String item;/*项目*/

    public String item2Cn(){
        String result = "";
        if (item != null) {
            item = item.trim();
            if ("1".equals(item)) {
                result = "利息支出";
            }else if ("2".equals(item)) {
                result = "利息收入";
            }else if ("3".equals(item)) {
                result = "汇兑损失";
            }else if ("4".equals(item)) {
                result = "汇兑收益";
            }else if ("5".equals(item)) {
                result = "其它";
            }else if ("6".equals(item)) {
                result = "合计";
            }
        }

        return result;
    }

    @FieldOut(cnName = "币种", order = 2)
    public String currency;/*币种*/

    @FieldOut(cnName = "本期金额", order = 3)
    public double amount;/*本期金额*/

    @FieldOut(cnName = "上期金额", order = 4)
    public double previousAmount;/*上期金额*/
}
