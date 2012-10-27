package jp.itacademy.samples.gae.controller.contact;

import jp.itacademy.samples.gae.model.Contact;

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
        v.add("tel", v.required());

        if (!v.validate()) {
            return forward("index");
        }

        String name = asString("name");
        String email = asString("email");
        String tel = asString("tel");

        Contact contact = new Contact(name, email, tel);
        Datastore.put(contact);

        return redirect("index");
    }
}
