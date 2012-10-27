package jp.itacademy.samples.gae.controller.tax;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class CalcController extends Controller {

    @Override
    public Navigation run() throws Exception {
        int price = asInteger("price");
        int excluded = (int) Math.ceil(price / 1.05);
        requestScope("excluded", excluded);
        return forward("calc.jsp");
    }
}
