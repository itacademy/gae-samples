package jp.itacademy.samples.gae.controller.taskqueue.item;

import jp.itacademy.samples.gae.model.Item;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;

public class EditController extends Controller {

    @Override
    public Navigation run() throws Exception {

        // �ҏW����A�C�e����ID���p�����[�^id�Ƃ��ēn����Ă��邩�m�F
        // �p�����[�^id���Ȃ��ꍇ�̓A�C�e���ꗗ�փ��_�C���N�g
        Validators v = new Validators(request);
        v.add("id", v.required(), v.longType());
        if (!v.validate()) {
            return redirect("/taskqueue/item/list");
        }

        // �f�[�^�X�g�A����Item�G���e�B�e�B���擾
        // id�ɊY������G���e�B�e�B��������Ȃ��ꍇ�̓A�C�e���ꗗ�փ��_�C���N�g
        Key key = Datastore.createKey(Item.class, asLong("id"));
        Item item = Datastore.getOrNull(Item.class, key);
        if (item == null) {
            return redirect("/taskqueue/item/list");
        }

        requestScope("id", item.getKey().getId());
        requestScope("name", item.getName());
        requestScope("price", item.getPrice());
        return forward("edit.jsp");
    }
}
