package jp.itacademy.samples.gae.controller.ds.employee;

import jp.itacademy.samples.gae.model.Employee;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;

public class Get1Controller extends Controller {

    public Navigation run() throws Exception {

        Key key = Datastore.createKey(Employee.class, 9999);
        Employee employee = Datastore.get(Employee.class, key);
        requestScope("employee", employee);

        return forward("get1.jsp");
    }
}
