package jp.itacademy.samples.gae.controller.memcache.employee;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import jp.itacademy.samples.gae.model.Dept;
import jp.itacademy.samples.gae.model.Employee;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.memcache.Memcache;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.memcache.Stats;

public class ListController extends Controller {

    private static Logger logger = Logger.getLogger(ListController.class
        .getName());

    @Override
    public Navigation run() throws Exception {

        List<Employee> employees = Datastore.query(Employee.class).asList();

        // まず取得する必要のある部署のキーを集める
        Set<Key> deptKeys = new HashSet<Key>();
        for (Employee e : employees) {
            deptKeys.add(e.getDeptKey());
        }

        // Memcacheから一括取得
        Map<Object, Object> deptMap = Memcache.getAll(deptKeys);

        // deptKeysに格納されている部署キーから、
        // MemcacheからDeptオブジェクトを取得できたものを取り除く
        // deptKeysに残されたキーがあれば、その部署オブジェクトはデータストアから取得しなくてはならない
        for (Iterator<Key> i = deptKeys.iterator(); i.hasNext();) {
            if (deptMap.containsKey(i.next())) {
                i.remove();
            }
        }

        // 残りの部署をデータストアから取得してdeptMapに追加する
        // これでdeptMapには必要なすべての部署オブジェクトが格納されたはず
        // データストアから取得した部署はMemcacheに格納しておく
        if (!deptKeys.isEmpty()) {
            Map<Key, Dept> map = Datastore.getAsMap(Dept.class, deptKeys);
            deptMap.putAll(map);
            Memcache.putAll(new HashMap<Object, Object>(map));
        }

        for (Employee e : employees) {
            e.setDept((Dept) deptMap.get(e.getDeptKey()));
        }

        requestScope("employees", employees);

        if (logger.isLoggable(Level.FINE)) {
            Stats stats = Memcache.statistics();
            logger.fine("hitCount: " + stats.getHitCount());
            logger.fine("missCount: " + stats.getMissCount());
            logger.fine("itemCount: " + stats.getItemCount());
            logger.fine("totalItemBytes: " + stats.getTotalItemBytes());
        }

        return forward("list.jsp");
    }
}
