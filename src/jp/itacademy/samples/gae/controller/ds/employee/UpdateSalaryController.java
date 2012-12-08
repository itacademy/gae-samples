package jp.itacademy.samples.gae.controller.ds.employee;

import jp.itacademy.samples.gae.model.Employee;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;

public class UpdateSalaryController extends Controller {

    @Override
    public Navigation run() throws Exception {

        Validators v = new Validators(request);
        v.add("id", v.required(), v.longType());
        v.add("salary", v.required(), v.integerType());
        if (!v.validate()) {
            System.out.println("validation error: " + errors);
            return null;
        }

        long id = asLong("id");
        int salary = asInteger("salary");

        Key key = Datastore.createKey(Employee.class, id);
        Transaction tx = Datastore.beginTransaction();
        try {
            Employee e = Datastore.get(tx, Employee.class, key);
            e.setSalary(salary);
            Datastore.put(tx, e);
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
        }

        return forward("updateSalary.jsp");
    }
}
