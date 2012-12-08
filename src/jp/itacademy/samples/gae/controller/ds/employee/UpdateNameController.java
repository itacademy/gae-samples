package jp.itacademy.samples.gae.controller.ds.employee;

import jp.itacademy.samples.gae.model.Employee;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;

public class UpdateNameController extends Controller {

    @Override
    public Navigation run() throws Exception {

        Validators v = new Validators(request);
        v.add("id", v.required(), v.longType());
        v.add("name", v.required());
        if (!v.validate()) {
            System.out.println("validation error: " + errors);
            return null;
        }

        long id = asLong("id");
        String name = asString("name");

        Key key = Datastore.createKey(Employee.class, id);
        Employee e = Datastore.get(Employee.class, key);
        e.setName(name);
        Datastore.put(e);

        return forward("updateName.jsp");
    }
}
