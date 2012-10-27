package jp.itacademy.samples.gae.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class HelloController extends Controller {

    @Override
    public Navigation run() throws Exception {
        return forward("hello.jsp");
    }
}
