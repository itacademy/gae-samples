package jp.itacademy.samples.gae.controller.library;

import java.util.Date;

import jp.itacademy.samples.gae.model.Book;
import jp.itacademy.samples.gae.model.Lend;
import jp.itacademy.samples.gae.model.User;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;

public class LendController extends Controller {

    @Override
    public Navigation run() throws Exception {

        Validators v = new Validators(request);
        v.add("bookId", v.required(), v.longType());
        v.add("userId", v.required(), v.longType());

        if (!v.validate()) {
            return forward("index");
        }

        long userId = asLong("userId");
        long bookId = asLong("bookId");
        Key userKey = Datastore.createKey(User.class, userId);
        Key bookKey = Datastore.createKey(Book.class, bookId);

        User user = Datastore.getOrNull(User.class, userKey);
        if (user == null) {
            return redirect("index");
        }

        Transaction tx = Datastore.beginTransaction();
        try {
            Book book = Datastore.getOrNull(Book.class, bookKey);
            if (book == null) {
                return redirect("index");
            }
            Lend lend = new Lend();
            lend.setKey(Datastore.allocateId(bookKey, Lend.class));
            lend.setUserId(userId);
            lend.setLendingDate(new Date());
            book.setRented(true);
            Datastore.put(lend, book);
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
        }

        return redirect("index");
    }
}
