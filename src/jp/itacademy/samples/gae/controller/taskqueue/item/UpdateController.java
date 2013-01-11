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

        // パラメータに不備があったら入力フォームを再表示
        Validators v = new Validators(request);
        v.add("id", v.required(), v.longType());
        v.add("name", v.required());
        v.add("price", v.required(), v.integerType());
        if (!v.validate()) {
            return forward("edit.jsp");
        }

        // データストアからItemエンティティを取得
        // idに該当するエンティティが見つからない場合はアイテム一覧へリダイレクト
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

            // URL /task/itemUpdate に、
            // パラメータ id と timestamp をつけてリクエストを送信するタスクを、
            // タスクキューに登録する。
            // タスクの登録はデータストアへのItemエンティティ保存と同一トランザクション内で。
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
