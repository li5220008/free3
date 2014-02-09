package dbutils.combhandler;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.RowProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 组合的BeanHandler
 * 代码来源于  org.apache.commons.dbutils.handlers.BeanHandler . 只是handle里不去调用 rs.next
 * User: wenzhihong
 * Date: 12-10-15
 * Time: 下午4:43
 */
public class CombBeanHandler<T> implements ResultSetHandler<T>, CombHandler {
    public static final RowProcessor ROW_PROCESSOR = new BasicRowProcessor();

    private final Class<T> type;

    private final RowProcessor convert;

    public CombBeanHandler(Class<T> type) {
        this(type, ROW_PROCESSOR);
    }

    public CombBeanHandler(Class<T> type, RowProcessor convert) {
        this.type = type;
        this.convert = convert;
    }


    @Override
    public T handle(ResultSet rs) throws SQLException {
        return this.convert.toBean(rs, this.type);
    }
}
