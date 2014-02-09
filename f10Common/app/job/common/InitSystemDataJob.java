package job.common;

import dbutils.ExtractDbUtil;
import dbutils.SqlLoader;
import dto.BondSec;
import dto.IndexInfo;
import dto.financing.Financing;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import play.Logger;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

import java.util.List;

/**
 * 应用程序启动时做数据初使化
 * User: wenzhihong
 * Date: 12-11-7
 * Time: 下午5:06
 */
@OnApplicationStart
public class InitSystemDataJob extends Job {
    @Override
    public void doJob() throws Exception {
        SqlLoader.init(); //加载sql资源文件
        BondSec.init(); //股票数据初始化
        IndexInfo.init(); //指数数据初始化
        initFinancingSecSet();
    }

    //初始化融资融券信息
    public static void initFinancingSecSet(){
        Logger.info("初始化融资融券信息------start");
        String sql = SqlLoader.getSqlById("allFinancingSecList");
        List<Long> secIdList = ExtractDbUtil.queryExtractDbWithHandler(sql, new ColumnListHandler<Long>());
        Financing.financingSecSet.addAll(secIdList);
        Logger.info("初始化融资融券信息------stop");
    }
}
