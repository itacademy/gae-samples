package jp.itacademy.samples.gae.controller.memcache;

import java.util.ArrayList;
import java.util.List;

import jp.itacademy.samples.gae.model.Dept;
import jp.itacademy.samples.gae.model.Employee;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

public class PrepareController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        List<Object> models = new ArrayList<Object>();
        models.add(new Dept(1, "�c�ƕ�"));
        models.add(new Dept(2, "�l����"));
        models.add(new Dept(3, "�J����"));
        models.add(new Employee(1, 1, "�c�Ƒ��Y"));
        models.add(new Employee(2, 1, "�c�Ǝ��Y"));
        models.add(new Employee(3, 2, "�l�����Y"));
        models.add(new Employee(4, 3, "�J�����鑠"));
        models.add(new Employee(5, 3, "�J�����v"));
        Datastore.put(models);
        
        return forward("prepare.jsp");
    }
}
