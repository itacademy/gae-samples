package jp.itacademy.samples.gae.controller.ds.punish;

import jp.itacademy.samples.gae.model.Employee;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;

public class NewController extends Controller {

    public Navigation run() throws Exception {

        // �p�����[�^employeeId���Ȃ����
        // �����o�^��ʂ͕\�������]�ƈ��ꗗ�Ƀ��_�C���N�g
        Validators v = new Validators(request);
        v.add("employeeId", v.required(), v.longType());
        if (!v.validate()) {
            return redirect("/ds/employee/all");
        }
        
        // �p�����[�^employeeId�������Ă�����ɊY������]�ƈ����݂���Ȃ�������
        // �����o�^��ʂ͕\�������]�ƈ��ꗗ�Ƀ��_�C���N�g
        Key key = Datastore.createKey(Employee.class, asLong("employeeId"));
        Employee employee = Datastore.getOrNull(Employee.class, key);
        if (employee == null) {
            return redirect("/ds/employee/all");
        }
        
        requestScope("employee", employee);
        return forward("new.jsp");
    }
}
