package jp.itacademy.samples.gae.controller.add;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class CalcController extends Controller {

    @Override
    public Navigation run() throws Exception {
        int num1 = asInteger("num1");
        int num2 = asInteger("num2");
        int result = num1 + num2;
        requestScope("result", result);
        return forward("calc.jsp");
    }
}
