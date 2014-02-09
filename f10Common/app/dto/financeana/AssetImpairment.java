package dto.financeana;

import util.CommonUtils;
import util.FieldOut;

/**
 * User: wenzhihong
 * Date: 12-12-24
 * Time: 下午1:36
 */
public class AssetImpairment {
    //输出的json对象. 每个对象包含属性 {cn:中文名, fn:json属性名}
    public static final String fieldOutJson = CommonUtils.fieldOutJson(AssetImpairment.class);

    public String enddateStr;

    @FieldOut(cnName = "项目", order = 1)
    public String item;/*项目*/

    @FieldOut(cnName = "期初余额", order = 2)
    public double beginningYearValue;/*期初余额*/

    @FieldOut(cnName = "本期计提", order = 3)
    public double currentProvision;/*本期增加额—本期计提*/

    @FieldOut(cnName = "其他", order = 4)
    public double addOther;/*本期增加额—其他*/

    @FieldOut(cnName = "转回", order = 5)
    public double currentReversal;/*本期减少额—转回*/

    @FieldOut(cnName = "转销", order = 6)
    public double currentReseller;/*本期减少额—转销*/

    @FieldOut(cnName = "其他", order = 7)
    public double lowerOther;/*本期减少额—其他*/

    @FieldOut(cnName = "期末余额", order = 8)
    public double endingYearValue; /*期末余额*/
}
