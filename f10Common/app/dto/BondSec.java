package dto;

import com.google.common.base.Objects;
import com.google.common.collect.*;
import com.tom.springutil.StopWatch;
import dbutils.ExtractDbUtil;
import dbutils.SqlLoader;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.AbstractKeyedHandler;
import org.apache.commons.lang.StringUtils;
import play.Logger;
import util.CommonUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * 股票信息, 关于一些股票的缓存等
 * User: wenzhihong
 * Date: 12-9-12
 * Time: 下午12:05
 */
public class BondSec {
    public static final String DEFAULT_CODE = "600000"; //个股

    private static final long ID_NO_VALUE = -999999; //公司id,secId无值定义. 这里就是定义一个无值的情况

    public static final String Type_A = "A"; //a股
    public static final String Type_B = "B"; //b股

    public long secId; //security id

    public String code; //代码

    public String name; //名称

    public String market; //市场, SZSE

    public long institutionId; //机构id, 也就是公司id

    public String shareType; //A,B股标识. A股为A, B股为B.

    //public BondIndu indu; //一级行业

    //public String induTwoCode; //二级行业代码

    //public Date delListedDate; //退市时间

    public BondSec(long secId, String scode, String sname, String market, long institutionId, String shareType){
        this.secId = secId;
        this.code = scode;
        this.name = sname;
        this.market = market;
        this.institutionId = institutionId;
        this.shareType = shareType;
    }

    //----------------------------------
    //全部股票信息
    public static Map<String, String> allSecMap = Maps.newHashMap();

    //股票代码与股票对照表
    //这里以股票代码(code)作为key值, 股票对象做为value
    public static final Map<String, BondSec> secMap = Maps.newHashMap();
    //以secId作为key值, 股票代码作为value值
    public static final Map<Long, String> secIdToCodeMap = Maps.newHashMap();
    //以公司代码做为key. 多个股票代码. (也就是一个公司多个股票)
    public static final ListMultimap<Long, String> institutionIdToCodeMap = ArrayListMultimap.create(3000, 2);

    //----------------------------------
    //行业代码名称映射. 代码做为key值, 中文名称value
    public static final Map<String, String> induCode2Name = Maps.newHashMap();

    //公司代码做为key, 行业代码做为value
    public static final Map<Long, String> institutionIdToInduCode = Maps.newHashMap();
    //行业代码与公司Id对应表
    //一级行业. 行业代码(一级)为key
    public static final ListMultimap<String, Long> induLev1CodeToSecIdMultimap = LinkedListMultimap.create(30);
    //二级行业. 行业代码(二级)为key
    public static final ListMultimap<String, Long> induLev2CodeToSecIdMultimap = LinkedListMultimap.create(90);
    //----------------------------------

    public static final int GROUP_SIZE = 30; //每组含有的个数

    //以下定义是用于job任务时, 按分组查询可以减少每次查询的结果集记录数. 会用于in 查询的一部分
    public static String[] secIdGroupArr; //secId 分组情况  每一项String都是分组的secId的字符串组装. 如 '10101','10202'
    public static String[] secCodeGroupArr;
    public static String[] institutionIdGroupArr;

    public static void clearData(){
        secMap.clear();
        secIdToCodeMap.clear();
        institutionIdToCodeMap.clear();
        induCode2Name.clear();
        institutionIdToInduCode.clear();
        induLev1CodeToSecIdMultimap.clear();
        induLev2CodeToSecIdMultimap.clear();
    }

    public static void init() {
        Logger.info("初始化股票数据开始...");
        //初始化股票信息(全部的)
        allSecMap = ExtractDbUtil.queryExtractDbWithHandler(SqlLoader.getSqlById("allStockInfo"), new AbstractKeyedHandler<String, String>() {

            @Override
            protected String createKey(ResultSet rs) throws SQLException {
                return rs.getString("scode");

            }

            @Override
            protected String createRow(ResultSet rs) throws SQLException {
                return rs.getString("name");
            }
        });


        boolean isDebug = Logger.isDebugEnabled();
        StopWatch sw = null;
        if (isDebug) {
            sw = new StopWatch("处理股票数据初始化");
        }

        //初始化股票
        String sql = SqlLoader.getSqlById("stock_info_init");

        BondSec.clearData();
        if (isDebug) {
            sw.start("从数据库加载数据");
        }

        ExtractDbUtil.queryExtractDbWithHandler(sql, new ResultSetHandler<Object>() {
            @Override
            public Object handle(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    String scode = rs.getString("scode");
                    long sid = rs.getLong("secId");
                    String name = rs.getString("name");
                    String market = rs.getString("market");
                    long institutionId = rs.getLong("institutionId");
                    String shareType = rs.getString("shareType");
                    if (shareType != null) {
                        shareType = shareType.toUpperCase();
                    }

                    if (BondSec.secMap.containsKey(scode) == false) { //不在的话,则增加
                        BondSec sec = new BondSec(sid, scode, name, market, institutionId, shareType);
                        BondSec.addSec(sec, true);
                    }
                }

                return null;
            }
        });
        if (isDebug) {
            sw.stop();
            sw.start("处理数据分组情况");
        }
        Map<String, BondSec> secMap = BondSec.secMap;
        //设置每组情况
        int groupNumber = secMap.size() % BondSec.GROUP_SIZE == 0 ? secMap.size() / BondSec.GROUP_SIZE : secMap.size() / BondSec.GROUP_SIZE + 1; //组的个数
        BondSec.secIdGroupArr = new String[groupNumber];
        BondSec.secCodeGroupArr = new String[groupNumber];
        BondSec.institutionIdGroupArr = new String[groupNumber];

        int count = 0;
        int arrIndex = 0;
        StringBuilder idSb = new StringBuilder();
        StringBuilder codeSb = new StringBuilder();
        StringBuilder orgId = new StringBuilder();
        for (String scode : secMap.keySet()) {
            BondSec sec = secMap.get(scode);
            idSb.append(sec.secId + ","); //在db里是bigint型, 所以不要用''
            orgId.append(sec.institutionId + ",");
            codeSb.append("'" + sec.code + "',"); //在db里是varchar型, 所以要用''

            count++;
            if (count % BondSec.GROUP_SIZE == 0 || count == secMap.size()) { //附值, 清空
                BondSec.secIdGroupArr[arrIndex] = idSb.substring(0, idSb.length() - 1);
                BondSec.secCodeGroupArr[arrIndex] = codeSb.substring(0, codeSb.length() - 1);
                BondSec.institutionIdGroupArr[arrIndex] = orgId.substring(0, orgId.length() - 1);

                arrIndex++;

                idSb.delete(0, idSb.length());
                codeSb.delete(0, codeSb.length());
                orgId.delete(0, orgId.length());
            }
        }

        if(isDebug){
            sw.stop();
            sw.start("处理公司与行业分类对应关系");
        }

        String sql3 = SqlLoader.getSqlById("institutionId2industryCode");
        final Set<String> induCode = Sets.newHashSet(); //行业代码
        ExtractDbUtil.queryExtractDbWithHandler(sql3, new ResultSetHandler<Object>(){

            @Override
            public Object handle(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    Long institutionId = rs.getLong("institutionId");
                    String industryCode = rs.getString("industryCode");

                    induCode.add(industryCode);
                    institutionIdToInduCode.put(institutionId, industryCode);

                    if (industryCode.length() >= 1) { //处理一级行业
                        induLev1CodeToSecIdMultimap.put(industryCode.substring(0, 1), institutionId);
                    }

                    if (industryCode.length() >= 3) { //处理二级行业
                        induLev2CodeToSecIdMultimap.put(industryCode.substring(0, 3), institutionId);
                    }
                }
                return null;
            }
        });

        //处理行业名称
        String sql4 = SqlLoader.getSqlById("induCodeName");
        sql4 = StringUtils.replace(sql4, "#codeList#", CommonUtils.sqlStrJoin(induCode));
        ExtractDbUtil.queryExtractDbWithHandler(sql4, new ResultSetHandler<Object>(){

                    @Override
                    public Object handle(ResultSet rs) throws SQLException {
                        while (rs.next()) {
                            String code = rs.getString("industrycode");
                            String name = rs.getString("industryname");
                            induCode2Name.put(code, name);
                        }
                        return null;
                    }
        });


        if (isDebug) {
            sw.stop();
            Logger.debug(sw.prettyPrint());
        }

        Logger.info("初始化股票数据结束...");
    }

    /**
     * 返回行业名称
     */
    public String fetchInduName(){
        String induCode = institutionIdToInduCode.get(this.institutionId);
        if(induCode == null){
            return null;
        }else{
            return induCode2Name.get(induCode);
        }
    }

    /**
     * 获取一级行业代码集合
     */
    public static Set<String> fetchLevelOneInduCodeList(){
        return BondSec.induLev1CodeToSecIdMultimap.keySet();
    }

    /**
     * 获取二级行业代码集合
     */
    public static Set<String> fetchLevelTwoInduCodeList(){
        return BondSec.induLev2CodeToSecIdMultimap.keySet();
    }


    /**
     * 通过股票代码取股票信息
     *
     * @param scode
     * @return
     */
    public static BondSec fetchBondSecByCode(String scode) {
        return secMap.get(scode);
    }

    /**
     * 通过 公司代码及股票类型 返回 股票对象.
     * @param institutionId 公司id
     * @param secType 股票类型  取值 BondSec.Type_A, BondSec.Type_B
     */
    public static BondSec fetchSecByInstitutionId(Long institutionId, String secType){
        List<String> scodeList = institutionIdToCodeMap.get(institutionId);
        if (scodeList == null || scodeList.size() == 0) {
            return null;
        }

        if (scodeList.size() == 1) {
            BondSec sec = secMap.get(scodeList.get(0));
            if (sec != null) {
                return sec;
            }else{
                return null;
            }
        }

        BondSec result = null;
        for (String s : scodeList) {
            BondSec sec = secMap.get(s);
            result = sec;
            if (sec != null) {
                if(secType.equalsIgnoreCase(sec.shareType)){
                    return sec;
                }
            }
        }

        return result;
    }

    public static String fetchScodeByInstitutionId(Long institutionId, String secType){
        BondSec sec = fetchSecByInstitutionId(institutionId, secType);
        return sec == null ? "" : sec.code;
    }

    /**
     * 取得公司id
     */
    public static long fetchInstitutionId(String scode){
        BondSec sec = secMap.get(scode);
        return sec != null ? sec.institutionId : ID_NO_VALUE ;
    }

    /**
     * 取得secId
     */
    public static long fetchSecId(String scode){
        BondSec sec = secMap.get(scode);
        return sec != null ? sec.secId : ID_NO_VALUE ;
    }

    /**
     * id(一般指institutionId或者secId)值是否有效.
     * 有效返回 true, 无效返回 false
     */
    public static boolean idIsValid(long id){
        return id != ID_NO_VALUE;
    }

    /**
     * 通过股票代码获取该股票的行业代码
     * @param scode
     * @return 没有的话, 返回null
     */
    public static String fetchInduCodeByCode(String scode) {
        BondSec sec = secMap.get(scode);
        if(sec == null){
            return null;
        }
        return institutionIdToInduCode.get(sec.institutionId);
    }

    public static String fetchInduCodeBySecId(Long secId){
        BondSec sec = fetchSecBySecId(secId);
        if (sec == null) {
            return null;
        }

        return institutionIdToInduCode.get(sec.institutionId);
    }

    /**
     * 通过 secid 返回该股票的二级行业代码
     * @return
     */
    public static String fetchInduLevel2CodeBySecId(Long secId){
        String s = fetchInduCodeBySecId(secId);
        if (s != null) {
            if (s.length() >= 3) {
                return s.substring(0, 3);
            }else {
                return s;
            }
        }

        return null;
    }

    /**
     * 通过股票代码获取该股票的二级行业代码
     * @param scode
     * @return 没有的话, 返回null
     */
    public static String fetchInduLevel2CodeByCode(String scode){
        String s = fetchInduCodeByCode(scode);
        if (s != null) {
            if (s.length() >= 3) {
                return s.substring(0, 3);
            }else{
                return s;
            }
        }

        return null;
    }

    /**
     * 是否金融股
     * @return
     */
    public boolean isFinancial(){
        List<String> financialInduCode = Lists.newArrayList("J66", "J67", "J68");
        String indu2Code = fetchInduLevel2CodeBySecId(this.secId);
        return indu2Code == null ? false : financialInduCode.contains(indu2Code);
    }

    /**
     * 根据 secId 取 BondSec 对象
     * @param secId
     * @return
     */
    public static BondSec fetchSecBySecId(Long secId){
        String code = secIdToCodeMap.get(secId);
        BondSec sec = secMap.get(code);
        return sec;
    }

    /**
     * 取同行业(二级行业)的股票. 如果没有的话, 返回空的list
     * @param scode
     * @return
     */
    public static List<BondSec> fetchSameIndu2LevelSecs(String scode){
        String induCode = fetchInduCodeByCode(scode);
        if(induCode == null){
            return Collections.emptyList();
        }else{
            String level2Code = induCode.length() >= 3 ? induCode.substring(0, 3) : induCode; //二级代码. 取前面的三位
            List<Long> secIdList = induLev2CodeToSecIdMultimap.get(level2Code);
            if (secIdList != null && secIdList.size() > 0) {
                List<BondSec> secList = Lists.newLinkedList();
                for (Long secId : secIdList) {
                    String code = secIdToCodeMap.get(secId);
                    BondSec sec = secMap.get(code);
                    if (sec != null) {
                        secList.add(sec);
                    }
                }
                return secList;
            }else{
                return Collections.emptyList();
            }
        }
    }

    /**
     *
     * @param sec
     * @param forceWrite 强制写
     */
    public static void addSec(BondSec sec, boolean forceWrite) {
        if (forceWrite) {
            secMap.put(sec.code, sec);
            secIdToCodeMap.put(sec.secId, sec.code);
            institutionIdToCodeMap.put(sec.institutionId, sec.code);
        } else {
            if (secMap.containsKey(sec.code) == false) {
                secMap.put(sec.code, sec);
                secIdToCodeMap.put(sec.secId, sec.code);
                institutionIdToCodeMap.put(sec.institutionId, sec.code);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BondSec)) return false;

        BondSec bondSec = (BondSec) o;

        if (!code.equals(bondSec.code)) return false;
        if (!name.equals(bondSec.name)) return false;
        if (!market.equals(bondSec.market)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code, name, market);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("code", code)
                .add("name", name)
                .add("market", market)
                .toString();
    }
}