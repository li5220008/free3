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
 * Time: 下午6:03
 */
public class T4CombinationListHandler<A, B, C, D> implements ResultSetHandler<List<F.T4<A, B, C, D>>> {
    ResultSetHandler<A> aHandler;
        ResultSetHandler<B> bHandler;
        ResultSetHandler<C> cHandler;
        ResultSetHandler<D> dHandler;

        public T4CombinationListHandler(ResultSetHandler<A> a, ResultSetHandler<B> b, ResultSetHandler<C> c, ResultSetHandler<D> d) {
            if (a instanceof CombHandler
                    && b instanceof CombHandler
                    && c instanceof CombHandler
                    && d instanceof CombHandler) {

                this.aHandler = a;
                this.bHandler = b;
                this.cHandler = c;
                this.dHandler = d;
            } else {
                throw new FastRuntimeException("这里必须是CombHandler类型, 请查阅util.combhandler包里的handler类型");
            }
        }


        @Override
        public List<F.T4<A, B, C, D>> handle(ResultSet rs) throws SQLException {
            List<F.T4<A, B, C, D>> listResult = new LinkedList<F.T4<A, B, C, D>>();
            while (rs.next()) {
                A objA = aHandler.handle(rs);
                B objB = bHandler.handle(rs);
                C objC = cHandler.handle(rs);
                D objD = dHandler.handle(rs);
                listResult.add(new F.T4(objA, objB, objC, objD));
            }
            return listResult;
        }
}
