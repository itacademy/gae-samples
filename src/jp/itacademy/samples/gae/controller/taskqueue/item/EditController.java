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

        // 編集するアイテムのIDがパラメータidとして渡されているか確認
        // パラメータidがない場合はアイテム一覧へリダイレクト
        Validators v = new Validators(request);
        v.add("id", v.required(), v.longType());
        if (!v.validate()) {
            return redirect("/taskqueue/item/list");
        }

        // データストアからItemエンティティを取得
        // idに該当するエンティティが見つからない場合はアイテム一覧へリダイレクト
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
