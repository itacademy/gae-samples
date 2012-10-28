package jp.itacademy.samples.gae.controller.todo;

import java.util.Date;

import jp.itacademy.samples.gae.model.Todo;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;

public class FinishController extends Controller {

    @Override
    public Navigation run() throws Exception {

        Validators v = new Validators(request);
        v.add("id", v.required(), v.longType());

        if (!v.validate()) {
            return forward("index");
        }

        long id = asLong("id");
        Key key = Datastore.createKey(Todo.class, id);
        Todo todo = Datastore.getOrNull(Todo.class, key);
        if (todo == null || todo.isFinished()) {
            return forward("index");
        }
        todo.setFinishDate(new Date());
        Datastore.put(todo);

        return redirect("index");
    }
}
