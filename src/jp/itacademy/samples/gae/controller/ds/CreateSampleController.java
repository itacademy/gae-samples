package jp.itacademy.samples.gae.controller.ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jp.itacademy.samples.gae.model.Credential;
import jp.itacademy.samples.gae.model.Dept;
import jp.itacademy.samples.gae.model.Employee;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

public class CreateSampleController extends Controller {

    @Override
    public Navigation run() throws Exception {

        Employee employee;
        Dept dept;
        Credential credential;
        List<Object> models = new ArrayList<Object>();

        // 資格エンティティの作成
        credential = new Credential();
        credential.setKey(Datastore.createKey(Credential.class, 1));
        credential.setName("普通免許");
        models.add(credential);

        credential = new Credential();
        credential.setKey(Datastore.createKey(Credential.class, 2));
        credential.setName("基本情報技術者");
        models.add(credential);

        credential = new Credential();
        credential.setKey(Datastore.createKey(Credential.class, 3));
        credential.setName("タコ検定");
        models.add(credential);

        // 部門エンティティの作成
        dept = new Dept();
        dept.setKey(Datastore.createKey(Dept.class, 1));
        dept.setName("営業");
        models.add(dept);

        dept = new Dept();
        dept.setKey(Datastore.createKey(Dept.class, 2));
        dept.setName("開発");
        models.add(dept);

        // 従業員エンティティの作成
        employee = new Employee();
        employee.setName("社畜太郎");
        employee.setHireDate(newDate(2012, 3, 1));
        employee.setDeptId(1);
        employee.setSalary(200000);
        employee.setCredentialIds(Arrays.asList(1L, 2L));
        models.add(employee);

        employee = new Employee();
        employee.setName("社畜次郎");
        employee.setHireDate(newDate(1999, 9, 9));
        employee.setDeptId(2);
        employee.setSalary(300000);
        employee.setCredentialIds(Arrays.asList(2L, 3L));
        models.add(employee);

        employee = new Employee();
        employee.setName("社畜三郎");
        employee.setHireDate(newDate(1980, 9, 9));
        employee.setDeptId(2);
        employee.setSalary(600000);
        employee.setCredentialIds(Arrays.asList(1L, 2L, 3L));
        models.add(employee);

        Datastore.put(models);

        return forward("createSample.jsp");
    }

    private Date newDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        return cal.getTime();
    }
}
