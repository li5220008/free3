package dto.financeana;

import util.CommonUtils;
import util.FieldOut;

/**
 * User: wenzhihong
 * Date: 12-12-24
 * Time: 下午1:52
 */
public class BusinessTaxAppend {
    public static final String fieldOutJson = CommonUtils.fieldOutJson(BusinessTaxAppend.class);

    public String enddateStr;

    @FieldOut(cnName = "税种", order = 1)
    public String taxType;/*税种*/

    @FieldOut(cnName = "币种", order = 2)
    public String currency;/*币种*/

    @FieldOut(cnName = "本期金额", order = 3)
    public double amount;/*本期金额*/

    @FieldOut(cnName = "上期金额", order = 4)
    public double previousAmount;/*上期金额*/

    @FieldOut(cnName = "计税基础", order = 5)
    public String taxBasis;/*计税基础*/
}
