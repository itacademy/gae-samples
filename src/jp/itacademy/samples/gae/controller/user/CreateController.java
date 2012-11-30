package jp.itacademy.samples.gae.controller.user;

import java.util.Date;

import jp.itacademy.samples.gae.model.User;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.datastore.Datastore;

public class CreateController extends Controller {

    @Override
    public Navigation run() throws Exception {

        Validators v = new Validators(request);
        v.add("name", v.required());
        v.add("email", v.required());

        if (!v.validate()) {
            return forward("index");
        }

        String name = asString("name");
        String email = asString("email");

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setCreateDate(new Date());
        Datastore.put(user);

        return redirect("index");
    }
}
