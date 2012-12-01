package jp.itacademy.samples.gae.controller.ds.employee;

import java.util.List;

import jp.itacademy.samples.gae.meta.EmployeeMeta;
import jp.itacademy.samples.gae.model.Employee;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

public class DriverListController extends Controller {

    public Navigation run() throws Exception {

        EmployeeMeta m = EmployeeMeta.get();
        List<Employee> employees =
            Datastore
                .query(m)
                .filter(m.credentialIds.equal(1L))
                .asList();

        requestScope("employees", employees);

        return forward("list.jsp");
    }
}
