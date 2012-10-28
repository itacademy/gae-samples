package jp.itacademy.samples.gae.controller.todo;

import java.util.Date;

import jp.itacademy.samples.gae.model.Todo;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.datastore.Datastore;

public class CreateController extends Controller {

    @Override
    public Navigation run() throws Exception {

        Validators v = new Validators(request);
        v.add("body", v.required());

        if (!v.validate()) {
            return forward("index");
        }

        String body = asString("body");

        Todo todo = new Todo(body);
        todo.setCreateDate(new Date());
        Datastore.put(todo);

        return redirect("index");
    }
}
