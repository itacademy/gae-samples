package jp.itacademy.samples.gae.controller.todo;

import java.util.List;

import jp.itacademy.samples.gae.meta.TodoMeta;
import jp.itacademy.samples.gae.model.Todo;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

public class FinishedListController extends Controller {

    @Override
    public Navigation run() throws Exception {
        TodoMeta m = TodoMeta.get();
        List<Todo> todos =
            Datastore
                .query(m)
                .filter(m.finishDate.notEqual(null))
                .sort(m.finishDate.desc)
                .asList();
        requestScope("todos", todos);
        return forward("finishedList.jsp");
    }
}
