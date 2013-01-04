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
        models.add(new Dept(1, "営業部"));
        models.add(new Dept(2, "人事部"));
        models.add(new Dept(3, "開発部"));
        models.add(new Employee(1, 1, "営業太郎"));
        models.add(new Employee(2, 1, "営業次郎"));
        models.add(new Employee(3, 2, "人事太郎"));
        models.add(new Employee(4, 3, "開発する蔵"));
        models.add(new Employee(5, 3, "開発やる夫"));
        Datastore.put(models);
        
        return forward("prepare.jsp");
    }
}
