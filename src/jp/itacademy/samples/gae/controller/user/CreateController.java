package jp.itacademy.samples.gae.controller.user;

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
        
        if (!v.validate()) {
            return forward("index");
        }
        
        String name = asString("name");
        
        User user = new User();
        user.setName(name);
        Datastore.put(user);
        
        return redirect("index");
    }
}
