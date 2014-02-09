package dbutils.combhandler;

import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 组合的ScalarHandler
 * 代码来源于  org.apache.commons.dbutils.handlers.ScalarHandler . 只是handle里不去调用 rs.next
 * User: wenzhihong
 * Date: 12-10-15
 * Time: 下午4:59
 */
public class CombScalarHandler<T> implements ResultSetHandler<T>, CombHandler {
    private final int columnIndex;

    private final String columnName;

    public CombScalarHandler() {
        this(1, null);
    }

    public CombScalarHandler(int columnIndex) {
        this(columnIndex, null);
    }

    public CombScalarHandler(String columnName) {
        this(1, columnName);
    }

    private CombScalarHandler(int columnIndex, String columnName) {
        this.columnIndex = columnIndex;
        this.columnName = columnName;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T handle(ResultSet rs) throws SQLException {
        if (this.columnName == null) {
            return (T) rs.getObject(this.columnIndex);
        } else {
            return (T) rs.getObject(this.columnName);
        }
    }

}
