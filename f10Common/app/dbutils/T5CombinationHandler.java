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
 * Time: 下午5:16
 */
public class T5CombinationHandler<A, B, C, D, E> implements ResultSetHandler<F.T5<A, B, C, D, E>> {
    ResultSetHandler<A> aHandler;
    ResultSetHandler<B> bHandler;
    ResultSetHandler<C> cHandler;
    ResultSetHandler<D> dHandler;
    ResultSetHandler<E> eHandler;


    public T5CombinationHandler(ResultSetHandler<A> a, ResultSetHandler<B> b, ResultSetHandler<C> c,
                                ResultSetHandler<D> d, ResultSetHandler<E> e) {
        if (a instanceof CombHandler
                && b instanceof CombHandler
                && c instanceof CombHandler
                && d instanceof CombHandler
                && d instanceof CombHandler) {

            this.aHandler = a;
            this.bHandler = b;
            this.cHandler = c;
            this.dHandler = d;
            this.eHandler = e;
        } else {
            throw new FastRuntimeException("这里必须是CombHandler类型, 请查阅util.combhandler包里的handler类型");
        }
    }

    @Override
    public F.T5<A, B, C, D, E> handle(ResultSet rs) throws SQLException {
        if (rs.next()) {
            A objA = aHandler.handle(rs);
            B objB = bHandler.handle(rs);
            C objC = cHandler.handle(rs);
            D objD = dHandler.handle(rs);
            E objE = eHandler.handle(rs);
            return new F.T5(objA, objB, objC, objD, objE);
        } else {
            return null;
        }
    }
}
