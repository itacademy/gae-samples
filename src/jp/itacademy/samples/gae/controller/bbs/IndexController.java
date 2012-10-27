package jp.itacademy.samples.gae.controller.bbs;

import java.util.List;

import jp.itacademy.samples.gae.model.BbsMessage;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
        List<BbsMessage> messages = Datastore.query(BbsMessage.class).asList();
        requestScope("messages", messages);
        return forward("index.jsp");
    }
}
