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

        // ���݂��Ȃ�����ID���N���C�A���g���瑗���Ă���\��������
        // Dept�G���e�B�e�B�����݂��邩�m�F���Ă���
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

        // �X�V���ꂽEmployee�I�u�W�F�N�g��Memcache����폜
        Memcache.delete(key);

        return forward("update.jsp");
    }
}
