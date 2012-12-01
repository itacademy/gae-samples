package jp.itacademy.samples.gae.controller.ds.employee;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jp.itacademy.samples.gae.model.Dept;
import jp.itacademy.samples.gae.model.Employee;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;

public class AllWithDeptController extends Controller {

    @Override
    public Navigation run() throws Exception {

        Set<Key> deptKeySet = new HashSet<Key>();

        List<Employee> employees = Datastore.query(Employee.class).asList();
        for (Employee e : employees) {
            if (e.getDeptId() != 0) {
                Key key = Datastore.createKey(Dept.class, e.getDeptId());
                deptKeySet.add(key);
            }
        }

        Map<Key, Dept> deptMap = Datastore.getAsMap(Dept.class, deptKeySet);

        for (Employee e : employees) {
            if (e.getDeptId() != 0) {
                Key key = Datastore.createKey(Dept.class, e.getDeptId());
                e.setDept(deptMap.get(key));
            }
        }
        requestScope("employees", employees);
        return forward("allWithDept.jsp");
    }
}
