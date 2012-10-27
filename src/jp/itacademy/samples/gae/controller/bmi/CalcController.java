package jp.itacademy.samples.gae.controller.bmi;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class CalcController extends Controller {

    @Override
    public Navigation run() throws Exception {
        double height = asDouble("height");
        double weight = asDouble("weight");
        double bmi = weight / Math.pow(height / 100, 2);
        requestScope("bmi", bmi);
        return forward("calc.jsp");
    }
}
