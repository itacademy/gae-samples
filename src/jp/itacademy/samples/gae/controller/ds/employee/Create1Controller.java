package jp.itacademy.samples.gae.controller.ds.employee;

import jp.itacademy.samples.gae.model.Employee;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;

public class Create1Controller extends Controller {

    public Navigation run() throws Exception {

        Employee employee = new Employee();
        employee.setName("ŽÐ’{ŽO˜Y");
        employee.setDeptId(1);
        Key key = Datastore.put(employee);
        requestScope("key", key);

        return forward("create1.jsp");
    }
}
