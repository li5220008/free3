package dto.financeana;

/**
 * 财务全景每一项
 * User: wenzhihong
 * Date: 12-12-22
 * Time: 上午11:34
 */
public class FullViewItem {
    public long institutionId;

    public String enddateStr;

    public double avgVal;

    //排名值(在0到5之间)
    public double stepVal;
}
