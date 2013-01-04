package jp.itacademy.samples.gae.controller.memcache.employee;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import jp.itacademy.samples.gae.model.Dept;
import jp.itacademy.samples.gae.model.Employee;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.memcache.Memcache;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.memcache.Stats;

public class ListController extends Controller {

    private static Logger logger = Logger.getLogger(ListController.class
        .getName());

    @Override
    public Navigation run() throws Exception {

        List<Employee> employees = Datastore.query(Employee.class).asList();

        // �܂��擾����K�v�̂��镔���̃L�[���W�߂�
        Set<Key> deptKeys = new HashSet<Key>();
        for (Employee e : employees) {
            deptKeys.add(e.getDeptKey());
        }

        // Memcache����ꊇ�擾
        Map<Object, Object> deptMap = Memcache.getAll(deptKeys);

        // deptKeys�Ɋi�[����Ă��镔���L�[����A
        // Memcache����Dept�I�u�W�F�N�g���擾�ł������̂���菜��
        // deptKeys�Ɏc���ꂽ�L�[������΁A���̕����I�u�W�F�N�g�̓f�[�^�X�g�A����擾���Ȃ��Ă͂Ȃ�Ȃ�
        for (Iterator<Key> i = deptKeys.iterator(); i.hasNext();) {
            if (deptMap.containsKey(i.next())) {
                i.remove();
            }
        }

        // �c��̕������f�[�^�X�g�A����擾����deptMap�ɒǉ�����
        // �����deptMap�ɂ͕K�v�Ȃ��ׂĂ̕����I�u�W�F�N�g���i�[���ꂽ�͂�
        // �f�[�^�X�g�A����擾����������Memcache�Ɋi�[���Ă���
        if (!deptKeys.isEmpty()) {
            Map<Key, Dept> map = Datastore.getAsMap(Dept.class, deptKeys);
            deptMap.putAll(map);
            Memcache.putAll(new HashMap<Object, Object>(map));
        }

        for (Employee e : employees) {
            e.setDept((Dept) deptMap.get(e.getDeptKey()));
        }

        requestScope("employees", employees);

        if (logger.isLoggable(Level.FINE)) {
            Stats stats = Memcache.statistics();
            logger.fine("hitCount: " + stats.getHitCount());
            logger.fine("missCount: " + stats.getMissCount());
            logger.fine("itemCount: " + stats.getItemCount());
            logger.fine("totalItemBytes: " + stats.getTotalItemBytes());
        }

        return forward("list.jsp");
    }
}
