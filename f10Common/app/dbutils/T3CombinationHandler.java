package dbutils;

import dbutils.combhandler.CombHandler;
import org.apache.commons.dbutils.ResultSetHandler;
import play.libs.F;
import play.utils.FastRuntimeException;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User: wenzhihong
 * Date: 12-10-15
 * Time: 下午5:10
 */
public class T3CombinationHandler<A, B, C> implements ResultSetHandler<F.T3<A, B, C>> {
    ResultSetHandler<A> aHandler;
    ResultSetHandler<B> bHandler;
    ResultSetHandler<C> cHandler;

    public T3CombinationHandler(ResultSetHandler<A> a, ResultSetHandler<B> b, ResultSetHandler<C> c) {
        if (a instanceof CombHandler
                && b instanceof CombHandler
                && c instanceof CombHandler) {

            this.aHandler = a;
            this.bHandler = b;
            this.cHandler = c;
        } else {
            throw new FastRuntimeException("这里必须是CombHandler类型, 请查阅util.combhandler包里的handler类型");
        }
    }


    @Override
    public F.T3<A, B, C> handle(ResultSet rs) throws SQLException {
        if (rs.next()) {
            A objA = aHandler.handle(rs);
            B objB = bHandler.handle(rs);
            C objC = cHandler.handle(rs);
            return new F.T3(objA, objB, objC);
        } else {
            return null;
        }
    }
}
