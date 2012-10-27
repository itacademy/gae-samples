package jp.itacademy.samples.gae.controller.book;

import java.util.Date;

import jp.itacademy.samples.gae.model.Book;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.datastore.Datastore;

public class CreateController extends Controller {

    @Override
    public Navigation run() throws Exception {

        Validators v = new Validators(request);
        v.add("title", v.required());
        v.add("author", v.required());
        v.add("price", v.required(), v.integerType());

        if (!v.validate()) {
            return forward("index");
        }

        String title = asString("title");
        String author = asString("author");
        int price = asInteger("price");

        Book book = new Book(title, author, price);
        book.setArrivalDate(new Date());
        Datastore.put(book);

        return redirect("index");
    }
}
