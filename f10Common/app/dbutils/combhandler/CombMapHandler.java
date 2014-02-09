package dbutils.combhandler;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.RowProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * 组合的MapHandler.
 * 代码来源于  org.apache.commons.dbutils.handlers.MapHandler . 只是handle里不去调用 rs.next
 * User: wenzhihong
 * Date: 12-10-15
 * Time: 下午4:56
 */
public class CombMapHandler implements ResultSetHandler<Map<String, Object>>, CombHandler {
    private final RowProcessor convert;

    public CombMapHandler() {
        this(CombBeanHandler.ROW_PROCESSOR);
    }

    public CombMapHandler(RowProcessor convert) {
        super();
        this.convert = convert;
    }

    @Override
    public Map<String, Object> handle(ResultSet rs) throws SQLException {
        return this.convert.toMap(rs);
    }

}
