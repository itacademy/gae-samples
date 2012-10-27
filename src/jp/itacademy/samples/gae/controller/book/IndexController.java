package jp.itacademy.samples.gae.controller.book;

import java.util.List;

import jp.itacademy.samples.gae.model.Book;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
        List<Book> books = Datastore.query(Book.class).asList();
        requestScope("books", books);
        return forward("index.jsp");
    }
}
