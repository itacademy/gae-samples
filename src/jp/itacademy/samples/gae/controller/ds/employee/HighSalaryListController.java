package jp.itacademy.samples.gae.controller.ds.employee;

import java.util.List;

import jp.itacademy.samples.gae.meta.EmployeeMeta;
import jp.itacademy.samples.gae.model.Employee;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

public class HighSalaryListController extends Controller {

    public Navigation run() throws Exception {

        EmployeeMeta m = EmployeeMeta.get();
        List<Employee> employees =
            Datastore
                .query(m)
                .filter(m.salary.greaterThanOrEqual(300000))
                .asList();

        requestScope("employees", employees);

        return forward("list.jsp");
    }
}
