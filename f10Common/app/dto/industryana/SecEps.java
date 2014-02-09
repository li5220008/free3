package dto.industryana;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.gson.annotations.SerializedName;
import dto.BondSec;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * 财务eps项
 * User: wenzhihong
 * Date: 12-12-15
 * Time: 下午12:41
 */
public class SecEps {
    //公司id
    @SerializedName("a")
    public Long institutionId;

    //排名
    @SerializedName("b")
    public int rank;

    //最近4期数据
    @SerializedName("c")
    public List<Item> detail = Lists.newArrayListWithCapacity(4);

    //排序
    public void sortItems(){
        Collections.sort(detail, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) { //倒序
                return Ordering.natural().reverse().nullsLast().compare(o1.endDate, o2.endDate);
                //return o2.endDate.compareTo(o1.endDate);
            }
        });
    }

    public void addItem(Item i){
        detail.add(i);
    }

    public static class Item {
        @SerializedName("d")
        public Date endDate;

        @SerializedName("e")
        public double eps;
    }

    public String secCode() {
        return BondSec.fetchScodeByInstitutionId(institutionId, BondSec.Type_A);
    }

    public String secName() {
        BondSec sec = BondSec.fetchSecByInstitutionId(institutionId, BondSec.Type_A);
        return sec == null ? "" : sec.name;
    }
}
