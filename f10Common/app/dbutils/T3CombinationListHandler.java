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
 * Time: 下午6:00
 */
public class T3CombinationListHandler<A, B, C> implements ResultSetHandler<List<F.T3<A, B, C>>> {
    ResultSetHandler<A> aHandler;
        ResultSetHandler<B> bHandler;
        ResultSetHandler<C> cHandler;

        public T3CombinationListHandler(ResultSetHandler<A> a, ResultSetHandler<B> b, ResultSetHandler<C> c) {
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
        public List<F.T3<A, B, C>> handle(ResultSet rs) throws SQLException {
            List<F.T3<A, B, C>> listResult = new LinkedList<F.T3<A, B, C>>();
            while (rs.next()) {
                A objA = aHandler.handle(rs);
                B objB = bHandler.handle(rs);
                C objC = cHandler.handle(rs);
                listResult.add(new F.T3(objA, objB, objC));
            }
            return listResult;
        }
}
