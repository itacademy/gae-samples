package jp.itacademy.samples.gae.controller.memcache.employee;

import jp.itacademy.samples.gae.model.Dept;
import jp.itacademy.samples.gae.model.Employee;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.datastore.Datastore;
import org.slim3.memcache.Memcache;

import com.google.appengine.api.datastore.Key;

public class UpdateController extends Controller {

    @Override
    public Navigation run() throws Exception {

        Validators v = new Validators(request);
        v.add("id", v.required(), v.longType());
        v.add("name", v.required());
        v.add("deptId", v.required(), v.longType());
        if (!v.validate()) {
            return redirect("list");
        }

        // 存在しない部署IDがクライアントから送られてくる可能性もある
        // Deptエンティティが存在するか確認しておく
        Key deptKey = Datastore.createKey(Dept.class, asLong("deptId"));
        Dept dept = Datastore.getOrNull(Dept.class, deptKey);
        if (dept == null) {
            return redirect("list");
        }

        Key key = Datastore.createKey(Employee.class, asLong("id"));
        Employee e = Datastore.getOrNull(Employee.class, key);
        e.setName(asString("name"));
        e.setDeptKey(deptKey);
        Datastore.put(e);

        // 更新されたEmployeeオブジェクトをMemcacheから削除
        Memcache.delete(key);

        return forward("update.jsp");
    }
}
