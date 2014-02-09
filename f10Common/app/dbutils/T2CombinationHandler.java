package dbutils;

import dbutils.combhandler.CombHandler;
import org.apache.commons.dbutils.ResultSetHandler;
import play.libs.F;
import play.utils.FastRuntimeException;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 由两个组合Handler处理结果集
 * User: wenzhihong
 * Date: 12-10-15
 * Time: 下午3:51
 */
public class T2CombinationHandler<A, B> implements ResultSetHandler<F.T2<A, B>> {

    ResultSetHandler<A> aHandler;
    ResultSetHandler<B> bHandler;

    public T2CombinationHandler(ResultSetHandler<A> a, ResultSetHandler<B> b) {
        if (a instanceof CombHandler
                && b instanceof CombHandler) {

            this.aHandler = a;
            this.bHandler = b;
        } else {
            throw new FastRuntimeException("这里必须是CombHandler类型, 请查阅dbutils.combhandler包里的handler类型");
        }
    }


    @Override
    public F.T2<A, B> handle(ResultSet rs) throws SQLException {
        if (rs.next()) {
            A objA = aHandler.handle(rs);
            B objB = bHandler.handle(rs);
            return new F.T2(objA, objB);
        } else {
            return null;
        }
    }
}
