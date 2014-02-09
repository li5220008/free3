package dto;

import com.google.common.collect.Maps;
import dbutils.ExtractDbUtil;
import dbutils.SqlLoader;
import org.apache.commons.dbutils.ResultSetHandler;
import play.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 指数信息
 * User: wenzhihong
 * Date: 12-11-24
 * Time: 下午7:53
 */
public class IndexInfo {
    public long secId;
    public String code; //代码
    public String name; //名称

    //code作为key值
    public static final Map<String, IndexInfo> codeMap = Maps.newHashMap();
    //指数id与code的对应关系
    public static final Map<Long, String> id2codeMap = Maps.newHashMap();

    //定义常用的指数代码
    //沪深300
    public static final String INDEX_HS300 = "000300";
    //上证综合指数
    public static final String INDEX_SH = "000001";
    //深证成份指数
    public static final String INDEX_SZ = "399001";
    //创业板指数（价格）
    public static final String INDEX_CYB = "399006";
    //中小板指数（价格）399005
    public static final String INDEX_ZXB = "399005";

    //指数代码
//    public static final String  index_code = "'000004', '000005', '000006', '000007', '000008', '399110', '399120', '399131', '399132', '399133', '399134', " +
//                "'399135', '399136', '399137', '399138', '399139', '399140', '399150', '399160', '399170', '399180', '399190', '399200'," +
//                "'399210', '399220', '399230', '000001', '399001', '399006', '399005', '000300'";

    public static final String  index_code = "'000004', '000005', '000006', '000007', '000008', " + //上海的
            "'399231', '399232', '399233', '399234', '399235', '399236', '399327', '399328', '399239', '399240', " + //深圳的
            "'399241', '399242', '399243', '399244', '399248', '399249'," +
            "'000001', '399001', '399006', '399005', '000300'";

    public static final String[] indexCodeArr = index_code.split(",");

    //行业指数与股票对应关系. key为股票代码, value为指数代码
    public static Map<String,String> indu_index_stock_relevance = Maps.newHashMap();

    //市场指数与股票对应关系. key为股票代码, value为指数代码
    public static Map<String,String> marketIndex_stock_relevance = Maps.newHashMap();

    //股票代码到行业指数
    public static IndexInfo secCode2InduIdx(String scode){
        String idxCode = indu_index_stock_relevance.get(scode);
        if(idxCode == null){
            return null;
        }else{
            return codeMap.get(idxCode) == null ? null : codeMap.get(idxCode);
        }
    }

    //股票代码到市场指数
    public static IndexInfo secCode2MarketIdx(String scode){
        String idxCode = marketIndex_stock_relevance.get(scode);
        if(idxCode == null){
            return null;
        }else{
            return codeMap.get(idxCode) == null ? null : codeMap.get(idxCode);
        }
    }

    //处理指数与行业对应关系
    public static void init() {
        Logger.info("处理指数与行业对应关系开始...");
        //加载指数信息
        String indexSql = SqlLoader.getSqlById("index_info").replaceAll("#index_code#", IndexInfo.index_code);
        List<IndexInfo> indexInfos = ExtractDbUtil.queryExtractDBBeanList(indexSql, IndexInfo.class);
        for (IndexInfo i : indexInfos) {
            IndexInfo.codeMap.put(i.code, i);
            IndexInfo.id2codeMap.put(i.secId, i.code);
        }

        //行业指数与股票
        String sql = SqlLoader.getSqlById("indu_index_stock_relevance");
        ExtractDbUtil.queryExtractDbWithHandler(sql, new ResultSetHandler<Object>() {
            @Override
            public Object handle(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    String indexCode = rs.getString("indexCode");
                    String scode = rs.getString("scode");
                    indu_index_stock_relevance.put(scode, indexCode);
                }
                return null;
            }
        });

        //市场指数与股票
        sql = SqlLoader.getSqlById("marketIndex_stock_relevance");
        ExtractDbUtil.queryExtractDbWithHandler(sql, new ResultSetHandler<Object>() {
            @Override
            public Object handle(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    String indexCode = rs.getString("indexCode");
                    String scode = rs.getString("scode");
                    marketIndex_stock_relevance.put(scode, indexCode);
                }
                return null;
            }
        });
        Logger.info("处理指数与行业对应关系结束...");
    }
}
