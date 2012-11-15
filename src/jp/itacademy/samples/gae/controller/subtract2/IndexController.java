package jp.itacademy.samples.gae.controller.subtract2;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
        return forward("index.jsp");
    }
}
