package jp.itacademy.samples.gae.controller.bbs;

import java.util.Date;

import jp.itacademy.samples.gae.model.BbsMessage;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.datastore.Datastore;

public class CreateController extends Controller {

    @Override
    public Navigation run() throws Exception {

        Validators v = new Validators(request);
        v.add("body", v.required(), v.maxlength(500));
        v.add("poster", v.required());

        if (!v.validate()) {
            return forward("index");
        }

        String body = asString("body");
        String poster = asString("poster");
        
        BbsMessage msg = new BbsMessage(body, poster);
        msg.setPostDate(new Date());
        Datastore.put(msg);

        return redirect("index");
    }
}
