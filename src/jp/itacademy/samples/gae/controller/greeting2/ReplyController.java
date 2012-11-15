package jp.itacademy.samples.gae.controller.greeting2;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

public class ReplyController extends Controller {

    @Override
    public Navigation run() throws Exception {

        Validators v = new Validators(request);
        v.add("name", v.required(), v.minlength(2), v.maxlength(10));

        if (!v.validate()) {
            return forward("index.jsp");
        }

        return forward("reply.jsp");
    }
}
