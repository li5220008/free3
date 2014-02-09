package dto.financeana;

import util.CommonUtils;
import util.FieldOut;

/**
 * User: wenzhihong
 * Date: 12-12-24
 * Time: 下午1:39
 */
public class EquityInvest {
    public static final String fieldOutJson = CommonUtils.fieldOutJson(EquityInvest.class);

    public String enddateStr;

    @FieldOut(cnName = "类别", order = 1)
    public String category;/*类别*/

    public String category2Cn() {
        if (category == null) {
            return "--";
        }
        //1 对子公司的投资

        category = category.trim();
        if ("1".equals(category)) {
            return "对子公司的投资";
        } else if ("2".equals(category)) {
            return "对联营合营企业投资";
        } else if ("3".equals(category)){
            return "其它投资";
        }else {
            return category;
        }

    }

    @FieldOut(cnName = "长期投资项目", order = 2)
    public String longtermInvestmentProjects;/*长期投资项目*/

    @FieldOut(cnName = "核算方法", order = 3)
    public String accountingMethods;/*核算方法*/

    @FieldOut(cnName = "投资成本", order = 4)
    public double investmentCost;/*投资成本*/

    @FieldOut(cnName = "期初余额", order = 5)
    public double beginningBalance;/*期初余额*/

    @FieldOut(cnName = "增减变动", order = 6)
    public double changesValue;/*增减变动*/

    @FieldOut(cnName = "期末余额", order = 7)
    public double endingBalance;/*期末余额*/

    @FieldOut(cnName = "持股比例(%)", order = 8)
    public String shareholdingScale;/*持股比例(%)*/

    public String fixShareholdingScale() {
        if (shareholdingScale == null || shareholdingScale.length() == 0) {
            return "--";
        }
        shareholdingScale = shareholdingScale.trim();
        if (shareholdingScale.charAt(0) == '.') {
            return "0" + shareholdingScale;
        }else {
            return shareholdingScale;
        }
    }

    @FieldOut(cnName = "表决权比例(%)", order = 9)
    public String voteScale;/*表决权比例(%)*/

    public String fixVoteScale(){
        if (voteScale == null || voteScale.length() == 0) {
            return "--";
        }
        voteScale = voteScale.trim();
        if (voteScale.charAt(0) == '.') {
            return "0" + voteScale;
        } else {
            return voteScale;
        }
    }

    @FieldOut(cnName = "币种", order = 10)
    public String currency;/*币种*/
}
