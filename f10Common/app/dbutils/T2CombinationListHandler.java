package dbutils;

import dbutils.combhandler.CombHandler;
import org.apache.commons.dbutils.ResultSetHandler;
import play.libs.F;
import play.utils.FastRuntimeException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * 取一个T2组合的list结果
 * User: wenzhihong
 * Date: 12-11-1
 * Time: 下午2:33
 */
public class T2CombinationListHandler<A, B> implements ResultSetHandler<List<F.T2<A, B>>> {
    ResultSetHandler<A> aHandler;
    ResultSetHandler<B> bHandler;

    public T2CombinationListHandler(ResultSetHandler<A> a, ResultSetHandler<B> b) {
        if (a instanceof CombHandler && b instanceof CombHandler) {
            this.aHandler = a;
            this.bHandler = b;
        } else {
            throw new FastRuntimeException("这里必须是CombHandler类型, 请查阅dbutils.combhandler包里的handler类型");
        }
    }

    @Override
    public List<F.T2<A, B>> handle(ResultSet rs) throws SQLException {
        List<F.T2<A, B>> resultList = new LinkedList<F.T2<A, B>>();
        while (rs.next()){
            A objA = aHandler.handle(rs);
            B objB = bHandler.handle(rs);
            resultList.add(new F.T2(objA, objB));
        }
        return resultList;
    }
}
