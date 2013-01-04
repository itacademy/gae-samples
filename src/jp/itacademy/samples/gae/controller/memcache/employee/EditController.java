package jp.itacademy.samples.gae.controller.memcache.employee;

import jp.itacademy.samples.gae.model.Dept;
import jp.itacademy.samples.gae.model.Employee;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;

public class EditController extends Controller {

    @Override
    public Navigation run() throws Exception {

        // long型に変換可能なパラメータidが必須
        // ない場合は従業員一覧画面にリダイレクト
        Validators v = new Validators(request);
        v.add("id", v.required(), v.longType());
        if (!v.validate()) {
            return redirect("list");
        }

        Key key = Datastore.createKey(Employee.class, asLong("id"));
        Employee e = Datastore.getOrNull(Employee.class, key);
        if (e == null) {
            return redirect("list");
        }

        requestScope("employee", e);
        requestScope("depts", Datastore.query(Dept.class).asList());
        return forward("edit.jsp");
    }
}
