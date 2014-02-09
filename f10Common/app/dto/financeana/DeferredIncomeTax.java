package dto.financeana;

import util.CommonUtils;
import util.FieldOut;

/**
 * User: wenzhihong
 * Date: 12-12-24
 * Time: 下午1:44
 */
public class DeferredIncomeTax {
    public static final String fieldOutJson = CommonUtils.fieldOutJson(DeferredIncomeTax.class);

    public String enddateStr;

    @FieldOut(cnName = "递延所得税类型", order = 1)
    public String deferredIncomeTaxType;/*递延所得税类型*/

    public String deferredIncomeTaxType2Cn() {
        if (deferredIncomeTaxType == null) {
            return "--";
        }
        deferredIncomeTaxType = deferredIncomeTaxType.trim();
        if ("1".equals(deferredIncomeTaxType)) {
            return "递延所得税资产";
        }else if ("2".equals(deferredIncomeTaxType)) {
            return "递延所得税负债";
        }else if ("3".equals(deferredIncomeTaxType)) {
            return "递延所得税";
        }else {
            return deferredIncomeTaxType;
        }


    }

    @FieldOut(cnName = "类别", order = 2)
    public String category;/*类别*/

    public String category2Cn() {
        if (category == null) {
            return "--";
        }
        category = category.trim();
        if ("1".equals(category)) {
            return "已确认";
        }else if ("2".equals(category)) {
            return "未确认";
        }else if ("3".equals(category)) {
            return "未确认的可抵扣亏损将于以下年度到期";
        }else if ("4".equals(category)) {
            return "应纳税差异和可抵扣差异项目(暂时性差异)";
        }else {
            return category;
        }

    }

    @FieldOut(cnName = "项目", order = 3)
    public String item;/*项目*/

    @FieldOut(cnName = "期初余额", order = 4)
    public double beginningYearValue;/*期初余额*/

    @FieldOut(cnName = "期末余额", order = 5)
    public double endingYearValue;/*期末余额*/
}
