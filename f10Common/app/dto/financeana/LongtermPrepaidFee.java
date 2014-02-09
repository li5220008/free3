package dto.financeana;

import util.CommonUtils;
import util.FieldOut;

/**
 * User: wenzhihong
 * Date: 12-12-24
 * Time: 下午1:42
 */
public class LongtermPrepaidFee {
    public static final String fieldOutJson = CommonUtils.fieldOutJson(LongtermPrepaidFee.class);

    public String enddateStr;

    @FieldOut(cnName = "项目", order = 1)
    public String item;/*项目*/

    @FieldOut(cnName = "期初余额", order = 2)
    public double beginningYearValue;/*期初余额*/

    @FieldOut(cnName = "本年增加", order = 3)
    public double increaseDuringYear;/*本年增加*/

    @FieldOut(cnName = "本年摊销", order = 4)
    public double amortizationYear;/*本年摊销*/

    @FieldOut(cnName = "其他减少", order = 5)
    public double decreaseDueOther;/*其他减少*/

    @FieldOut(cnName = "期末余额", order = 6)
    public double endingYearValue;/*期末余额*/
}
