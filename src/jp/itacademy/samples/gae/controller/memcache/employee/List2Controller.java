package jp.itacademy.samples.gae.controller.memcache.employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.itacademy.samples.gae.model.Dept;
import jp.itacademy.samples.gae.model.Employee;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.memcache.Memcache;

import com.google.appengine.api.datastore.Key;

public class List2Controller extends Controller {

    private static final String ALL_DEPT_MAP_KEY = Dept.class.getName()
        + ".ALL_MAP";

    @Override
    public Navigation run() throws Exception {

        Map<Key, Dept> deptMap = Memcache.get(ALL_DEPT_MAP_KEY);
        if (deptMap == null) {
            deptMap = new HashMap<Key, Dept>();
            List<Dept> depts = Datastore.query(Dept.class).asList();
            for (Dept dept : depts) {
                deptMap.put(dept.getKey(), dept);
            }
            Memcache.put(ALL_DEPT_MAP_KEY, deptMap);
        }

        List<Employee> employees = Datastore.query(Employee.class).asList();
        for (Employee e : employees) {
            e.setDept(deptMap.get(e.getDeptKey()));
        }

        requestScope("employees", employees);
        System.out.println(Memcache.statistics().getHitCount());
        return forward("list.jsp");
    }
}
