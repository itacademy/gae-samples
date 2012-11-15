package jp.itacademy.samples.gae.controller.subtract2;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

public class CalcController extends Controller {

    @Override
    public Navigation run() throws Exception {

        Validators v = new Validators(request);
        v.add("num1", v.required(), v.integerType());
        v.add("num2", v.required(), v.integerType());

        if (!v.validate()) {
            return forward("index.jsp");
        }

        int num1 = asInteger("num1");
        int num2 = asInteger("num2");
        int result = num1 - num2;
        requestScope("result", result);
        return forward("calc.jsp");
    }
}
