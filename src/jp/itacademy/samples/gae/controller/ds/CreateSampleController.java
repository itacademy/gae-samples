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

        // ���i�G���e�B�e�B�̍쐬
        credential = new Credential();
        credential.setKey(Datastore.createKey(Credential.class, 1));
        credential.setName("���ʖƋ�");
        models.add(credential);

        credential = new Credential();
        credential.setKey(Datastore.createKey(Credential.class, 2));
        credential.setName("��{���Z�p��");
        models.add(credential);

        credential = new Credential();
        credential.setKey(Datastore.createKey(Credential.class, 3));
        credential.setName("�^�R����");
        models.add(credential);

        // ����G���e�B�e�B�̍쐬
        dept = new Dept();
        dept.setKey(Datastore.createKey(Dept.class, 1));
        dept.setName("�c��");
        models.add(dept);

        dept = new Dept();
        dept.setKey(Datastore.createKey(Dept.class, 2));
        dept.setName("�J��");
        models.add(dept);

        // �]�ƈ��G���e�B�e�B�̍쐬
        employee = new Employee();
        employee.setName("�В{���Y");
        employee.setHireDate(newDate(2012, 3, 1));
        employee.setDeptId(1);
        employee.setSalary(200000);
        employee.setCredentialIds(Arrays.asList(1L, 2L));
        models.add(employee);

        employee = new Employee();
        employee.setName("�В{���Y");
        employee.setHireDate(newDate(1999, 9, 9));
        employee.setDeptId(2);
        employee.setSalary(300000);
        employee.setCredentialIds(Arrays.asList(2L, 3L));
        models.add(employee);

        employee = new Employee();
        employee.setName("�В{�O�Y");
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
