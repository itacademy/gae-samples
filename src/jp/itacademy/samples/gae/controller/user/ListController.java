package jp.itacademy.samples.gae.controller.user;

import java.util.List;

import jp.itacademy.samples.gae.model.User;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

public class ListController extends Controller {

    @Override
    public Navigation run() throws Exception {
        List<User> users = Datastore.query(User.class).asList();
        requestScope("users", users);
        return forward("list.jsp");
    }
}
