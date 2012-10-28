package jp.itacademy.samples.gae.controller.todo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jp.itacademy.samples.gae.meta.TodoMeta;
import jp.itacademy.samples.gae.model.Todo;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

public class IndexController extends Controller {

    TodoMeta m = TodoMeta.get();

    @Override
    public Navigation run() throws Exception {
        List<Todo> todos = Datastore.query(m).asList();
        List<Todo> f = new ArrayList<Todo>();
        List<Todo> u = new ArrayList<Todo>();
        for (Todo todo : todos) {
            (todo.isFinished() ? f : u).add(todo);
        }
        Collections.sort(f, Todo.FINISH_DATE_COMPARATOR_R);
        Collections.sort(u, Todo.CREATE_DATE_COMPARATOR_R);
        requestScope("finishedTodos", f);
        requestScope("unfinishedTodos", u);
        return forward("index.jsp");
    }

    // 次のようにフィルタリングを行うと、未完了・完了済を別々に取得できる
    // コードはこちらのほうが読みやすいが、
    // どうせ両方取得するのなら、まとめて取得したほうが効率はよい。
    protected List<Todo> getUnfinishedTodos() {
        return Datastore
            .query(m)
            .filter(m.finishDate.equal(null))
            .sort(m.createDate.desc)
            .asList();
    }

    protected List<Todo> getFinishedTodos() {
        return Datastore
            .query(m)
            .filter(m.finishDate.isNotNull())
            .sort(m.finishDate.desc)
            .asList();
    }
}
