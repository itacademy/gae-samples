package jp.itacademy.samples.gae.controller.library;

import java.util.List;

import jp.itacademy.samples.gae.model.Book;
import jp.itacademy.samples.gae.model.User;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
        List<Book> books = Datastore.query(Book.class).asList();
        List<User> users = Datastore.query(User.class).asList();
        requestScope("books", books);
        requestScope("users", users);
        return forward("index.jsp");
    }
}
