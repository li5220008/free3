package dto.industryana;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 行业的.这里只是一个组合体
 * User: wenzhihong
 * Date: 12-12-29
 * Time: 上午11:06
 */
public class AppraisementFullIndu {
    //这个行业的全部股票的
    public List<AppraisementFullItem> list = Lists.newArrayList();

    //这个行业的平均
    public AppraisementFullItem avg;

    //这个行业的中位值
    public AppraisementFullItem middle;
}
