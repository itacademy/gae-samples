package jp.itacademy.samples.gae.controller.taskqueue.item;

import jp.itacademy.samples.gae.model.Item;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;

public class UpdateController extends Controller {

    @Override
    public Navigation run() throws Exception {

        // �p�����[�^�ɕs��������������̓t�H�[�����ĕ\��
        Validators v = new Validators(request);
        v.add("id", v.required(), v.longType());
        v.add("name", v.required());
        v.add("price", v.required(), v.integerType());
        if (!v.validate()) {
            return forward("edit.jsp");
        }

        // �f�[�^�X�g�A����Item�G���e�B�e�B���擾
        // id�ɊY������G���e�B�e�B��������Ȃ��ꍇ�̓A�C�e���ꗗ�փ��_�C���N�g
        Key key = Datastore.createKey(Item.class, asLong("id"));

        Transaction tx = Datastore.beginTransaction();
        try {
            Item item = Datastore.getOrNull(tx, Item.class, key);
            if (item == null) {
                return redirect("/taskqueue/item/list");
            }
            item.setName(asString("name"));
            item.setPrice(asInteger("price"));
            Datastore.put(tx, item);

            // URL /task/itemUpdate �ɁA
            // �p�����[�^ id �� timestamp �����ă��N�G�X�g�𑗐M����^�X�N���A
            // �^�X�N�L���[�ɓo�^����B
            // �^�X�N�̓o�^�̓f�[�^�X�g�A�ւ�Item�G���e�B�e�B�ۑ��Ɠ���g�����U�N�V�������ŁB
            long timestamp = System.currentTimeMillis();
            Queue queue = QueueFactory.getDefaultQueue();
            queue.add(tx, TaskOptions.Builder
                .withUrl("/task/itemUpdate")
                .param("id", String.valueOf(key.getId()))
                .param("timestamp", String.valueOf(timestamp)));

            tx.commit();

        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
        }

        return redirect("/taskqueue/item/list");
    }
}
