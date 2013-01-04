package jp.itacademy.samples.gae.controller.memcache.employee;

import javax.servlet.http.HttpServletResponse;

import jp.itacademy.samples.gae.model.Employee;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.datastore.Datastore;
import org.slim3.memcache.Memcache;

import com.google.appengine.api.datastore.Key;

public class ShowController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        // パラメータidは必須、かつlong型値に変換できなくてはならない
        Validators v = new Validators(request);
        v.add("id", v.required(), v.longType());
        if (!v.validate()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        Key key = Datastore.createKey(Employee.class, asLong("id"));
        Employee e = Memcache.get(key);
        if (e == null) {
            e = Datastore.getOrNull(Employee.class, key);
            if (e == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return null;
            }
            Memcache.put(key, e);
        }
        
        requestScope("employee", e);
        
        return forward("show.jsp");
    }
}
