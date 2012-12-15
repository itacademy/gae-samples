package jp.itacademy.samples.gae.controller.ds.account;

import jp.itacademy.samples.gae.model.Account;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.TransactionOptions;

public class TransferController extends Controller {

    @Override
    public Navigation run() throws Exception {

        TransactionOptions opt = TransactionOptions.Builder.withXG(true);
        Transaction tx =
            DatastoreServiceFactory.getDatastoreService().beginTransaction(opt);

        try {
            Key key1 = Datastore.createKey(Account.class, "000001");
            Account a1 = Datastore.get(Account.class, key1);

            Key key2 = Datastore.createKey(Account.class, "000002");
            Account a2 = Datastore.get(Account.class, key2);

            // a1Ç©ÇÁa2Ç÷10ñúâ~êUçû
            a1.withdraw(100000);
            a2.deposit(100000);

            Datastore.put(a1, a2);
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
        }

        return forward("transfer.jsp");
    }
}
