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
 * User: wenzhihong
 * Date: 12-11-1
 * Time: 下午6:05
 */
public class T5CombinationListHandler<A, B, C, D, E> implements ResultSetHandler<List<F.T5<A, B, C, D, E>>> {
    ResultSetHandler<A> aHandler;
    ResultSetHandler<B> bHandler;
    ResultSetHandler<C> cHandler;
    ResultSetHandler<D> dHandler;
    ResultSetHandler<E> eHandler;


    public T5CombinationListHandler(ResultSetHandler<A> a, ResultSetHandler<B> b, ResultSetHandler<C> c,
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
    public List<F.T5<A, B, C, D, E>> handle(ResultSet rs) throws SQLException {
        List<F.T5<A, B, C, D, E>> listResult = new LinkedList<F.T5<A, B, C, D, E>>();
        while (rs.next()) {
            A objA = aHandler.handle(rs);
            B objB = bHandler.handle(rs);
            C objC = cHandler.handle(rs);
            D objD = dHandler.handle(rs);
            E objE = eHandler.handle(rs);
            listResult.add(new F.T5(objA, objB, objC, objD, objE));
        }

        return listResult;
    }
}
