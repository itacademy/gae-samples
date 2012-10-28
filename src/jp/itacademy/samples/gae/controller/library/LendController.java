package jp.itacademy.samples.gae.controller.library;

import java.util.logging.Logger;

import jp.itacademy.samples.gae.model.Book;
import jp.itacademy.samples.gae.model.User;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;

public class LendController extends Controller {

    private static Logger logger = Logger.getLogger(LendController.class
        .getName());

    @Override
    public Navigation run() throws Exception {

        Validators v = new Validators(request);
        v.add("bookId", v.required(), v.longType());
        v.add("userId", v.required(), v.longType());

        if (!v.validate()) {
            return forward("index");
        }

        long bookId = asLong("bookId");
        long userId = asLong("userId");
        Key bookKey = Datastore.createKey(Book.class, bookId);
        Key userKey = Datastore.createKey(User.class, userId);

        Book book = Datastore.getOrNull(Book.class, bookKey);
        User user = Datastore.getOrNull(User.class, bookKey);
        logger.fine("book: " + book + " / user: " + user);

        if (book == null || user == null) {
            return redirect("index");
        }

        return redirect("index");
    }
}
