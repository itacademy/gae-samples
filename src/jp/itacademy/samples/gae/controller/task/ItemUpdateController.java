package jp.itacademy.samples.gae.controller.task;

import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.logging.Logger;

import jp.itacademy.samples.gae.meta.SaleItemMeta;
import jp.itacademy.samples.gae.model.Item;
import jp.itacademy.samples.gae.model.SaleItem;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;

public class ItemUpdateController extends Controller {

    private static final Logger logger = Logger
        .getLogger(ItemUpdateController.class.getName());

    @Override
    public Navigation run() throws Exception {

        // 変更されたアイテムのIDがパラメータidとして渡されているか確認
        // このコントローラはユーザがブラウザからアクセスするわけではなく、
        // プログラム内からタスクとしてタスクキューに登録して実行されることになるので、
        // パラメータに不備があるということは、
        // タスクとして登録する側のプログラムが間違っている（＝バグ）ということ
        // 従って、パラメータの不備への対応としては、警告ログを出力するだけでよい
        Validators v = new Validators(request);
        v.add("id", v.required(), v.longType());
        v.add("timestamp", v.required(), v.longType());
        if (!v.validate()) {
            logger.warning("パラメータが正しくありません: " + errors);
            return null;
        }

        long id = asLong("id");
        long timestamp = asLong("timestamp");

        Key key = Datastore.createKey(Item.class, id);
        Item item = Datastore.getOrNull(Item.class, key);
        if (item == null) {
            logger.warning("パラメータidに該当するアイテムがありません: " + id);
            return null;
        }

        SaleItemMeta m = SaleItemMeta.get();

        while (true) {

            // 変更されたItemと関連していて、
            // Itemの変更日時以降に更新が行われていないSaleItemを取得する。
            // ただし、SaleItemエンティティのリストではなく、そのキーのリストを取得している。
            // 理由は後述。
            List<Key> saleItemKeys =
                Datastore
                    .query(m)
                    .filter(
                        m.itemKey.equal(key),
                        m.lastUpdateTimestamp.lessThan(timestamp))
                    .asKeyList();

            // 一件もなければそこでタスクは終了
            if (saleItemKeys.isEmpty()) {
                break;
            }

            // 上で取得したSaleItemのキーリストを使って、
            // エンティティひとつずつアイテム名・価格・最終更新タイムスタンプを更新していく。
            // 同じSaleItemに対する処理が他のタスクと同時に実行される可能性を考慮して、
            // ひとつずつトランザクション内で更新を行なっている。
            //
            // 上でSaleItemのリストではなくキーのリストを取得している理由は、
            // どのみちトランザクション内でもう一度SaleItemエンティティをひとつずつ取得しなおす必要があるから。
            for (Key saleItemKey : saleItemKeys) {

                Transaction tx = Datastore.beginTransaction();

                try {
                    SaleItem saleItem =
                        Datastore.get(tx, SaleItem.class, saleItemKey);

                    // 最初にキーのリストを取得した瞬間からここまでの間に
                    // 別のタスクによってSaleItemエンティティが更新されている可能性があるので、
                    // トランザクション内でタイムスタンプを再確認しておく
                    if (saleItem.getLastUpdateTimestamp() >= timestamp) {
                        continue;
                    }
                    saleItem.setItemName(item.getName());
                    saleItem.setItemPrice(item.getPrice());
                    saleItem.setLastUpdateTimestamp(timestamp);
                    Datastore.put(tx, saleItem);
                    tx.commit();

                } catch (ConcurrentModificationException e) {
                    // 万一、処理中に同じSaleItemが別のタスクによって更新された場合は
                    // 一旦そのSaleItemの処理を保留して次のSaleItemへ
                    continue;
                } finally {
                    if (tx.isActive()) {
                        tx.rollback();
                    }
                }
            }
        }

        return null;
    }
}
