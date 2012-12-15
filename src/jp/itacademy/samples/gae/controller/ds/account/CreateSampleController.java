package jp.itacademy.samples.gae.controller.ds.account;

import jp.itacademy.samples.gae.model.Account;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

public class CreateSampleController extends Controller {

    @Override
    public Navigation run() throws Exception {

        Account a1 = new Account();
        a1.setKey(Datastore.createKey(Account.class, "000001"));
        a1.setBalance(200000);

        Account a2 = new Account();
        a2.setKey(Datastore.createKey(Account.class, "000002"));
        a2.setBalance(1000);

        Datastore.put(a1, a2);

        return forward("createSample.jsp");
    }
}
