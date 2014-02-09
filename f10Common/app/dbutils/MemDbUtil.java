package dbutils;

import com.google.common.collect.Lists;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.*;
import play.db.DB;
import play.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 本地数据库数据库操作
 * User: wenzhihong
 * Date: 13-1-21
 * Time: 下午3:41
 */
public class MemDbUtil {
    public static QueryRunner customRunner = new QueryRunner();

    //提取数据的数据库在配制中的名称, 对应于application.conf里的 db_名称.url的配制信息. 如果要改动请两个一起改.
    public static final String MEM_DB_CONF_NAME = "mem";

    //把数据库查询的行处理成 Map
    public static final RowProcessor MAP_ROW_PROCESSOR = new MapRowProcessor();

    //处理一行
    public static final RowProcessor ROW_PROCESSOR = new BasicRowProcessor();

    /**
     * 返回提取数据的数据库连接
     */
    public static Connection getConnection() {
        return DB.getDBConfig(MEM_DB_CONF_NAME, false).getConnection();
    }

    /**
     * 在提取数据的数据库上执行sql. (一般是执行对数据库有更新的那种)
     */
    public static boolean execute(String SQL) {
        return DB.getDBConfig(MEM_DB_CONF_NAME, false).execute(SQL);
    }

    /**
     * 在提取数据的数据库上执行sql语句(查询类)
     */
    public static ResultSet executeQuery(String SQL) {
        return DB.getDBConfig(MEM_DB_CONF_NAME, false).executeQuery(SQL);
    }

    /**
     * 查询一列一行. 如果没有的话, 返回null
     */
    public static <T> T queryOneFieldOneRow(String sql, Class<T> cl, Object... params){
        Connection conn = getConnection();
        ResultSetHandler<T> h = new ScalarHandler<T>();
        T t = null;
        try {
            t = customRunner.query(conn, sql, h, params);
            return t;
        } catch (SQLException ex) {
            throw new DatabaseException(ex.getMessage(), ex);
        }
    }

    /**
     * 查询sql返回单个bean. 如果没有的话, 返回null
     */
    public static <T> T querySingleBean(String sql, Class<T> cl, Object... params) {
        Connection conn = getConnection();
        ResultSetHandler<T> h = new BeanHandler<T>(cl);
        T t = null;
        try {
            t = customRunner.query(conn, sql, h, params);
            return t;
        } catch (SQLException ex) {
            throw new DatabaseException(ex.getMessage(), ex);
        }
    }

    /**
     * 查询sql返回Bean list. 如果没有的话, 返回的list长度为0
     */
    public static <T> List<T> queryBeanList(String sql, Class<T> cl, Object... params) {
        Connection conn = getConnection();
        ResultSetHandler<List<T>> h = new BeanListHandler<T>(cl);
        try {
            return customRunner.query(conn, sql, h, params);
        } catch (SQLException ex) {
            throw new DatabaseException(ex.getMessage(), ex);
        }
    }

    /**
     * 查询单条记录, 转成一个map. 注意, 这里的map的key值为小写的
     */
    public static Map<String, Object> querySingleMap(String sql, Object... params) {
        Connection conn = getConnection();
        ResultSetHandler<Map<String, Object>> h = new MapHandler(MAP_ROW_PROCESSOR);
        Map t = null;
        try {
            t = customRunner.query(conn, sql, h, params);
        } catch (SQLException ex) {
            throw new DatabaseException(ex.getMessage(), ex);
        }
        return t;
    }

    /**
     * 用handler处理查询记录
     *
     * @return
     */
    public static <T> T queryWithHandler(String sql, ResultSetHandler<T> rsh, Object... params) {
        Connection conn = getConnection();
        try {
            return customRunner.query(conn, sql, rsh, params);
        } catch (SQLException ex) {
            throw new DatabaseException(ex.getMessage(), ex);
        }
    }

    /**
     * 查询多条记录, 转成list<map>. 注意, 这里的map的key值为小写的
     * 如果没有,则返回的list长度为0
     */
    public static List<Map<String, Object>> queryMapList(String sql, Object... params) {
        Connection conn = getConnection();
        ResultSetHandler<List<Map<String, Object>>> h = new MapListHandler(MAP_ROW_PROCESSOR);
        try {
            return customRunner.query(conn, sql, h, params);
        } catch (SQLException ex) {
            throw new DatabaseException(ex.getMessage(), ex);
        }
    }


    /**
     * 用于count语句的.只查总数
     *
     * @return
     */
    public static Long queryCount(String sql, Object... params) {
        Connection conn = getConnection();
        try {
            return customRunner.query(conn, sql, new ScalarHandler<Long>(), params);
        } catch (SQLException ex) {
            throw new DatabaseException(ex.getMessage(), ex);
        }
    }

    /**
     * 可以执行sql语句. update, delete
     * @param sql sql语句
     * @param param sql语句里的参数
     * @return 受sql语句影响的记录条数
     */
    public static int update(String sql, Object... param){
        Connection conn = getConnection();
        try {
            return customRunner.update(conn, sql, param);
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage(), e);
        }
    }

    /**
     * 用于insert 语句. 返回自动增长的id值. 失败则返回 Long.MIN_VALUE
     * @param sql
     * @param param
     * @return
     */
    public static long insert(String sql, Object... param){
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            customRunner.fillStatement(pstmt, param);
            pstmt.executeUpdate();
            //返回自增加id
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            }
            return Long.MIN_VALUE;
        }catch (SQLException ex){
            throw new DatabaseException(ex.getMessage(), ex);
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(pstmt);
        }
    }

    /**
     * 批量插入. 返回每条语句的的自动增长id
     * @param sql
     * @param params
     * @return
     */
    public static List<Long> batchInsert(String sql,  Object[][] params){
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++) {
                customRunner.fillStatement(pstmt, params[i]);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            rs = pstmt.getGeneratedKeys();
            List<Long> idLists = Lists.newLinkedList();
            while(rs.next()){
                idLists.add(rs.getLong(1));
            }
            return idLists;
        } catch (SQLException ex) {
            throw new DatabaseException(ex.getMessage(), ex);
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(pstmt);
        }
    }
}
