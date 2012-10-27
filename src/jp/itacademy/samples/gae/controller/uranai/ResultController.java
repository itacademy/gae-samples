package jp.itacademy.samples.gae.controller.uranai;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class ResultController extends Controller {

    @Override
    public Navigation run() throws Exception {

        String bloodType = asString("bloodType");

        String result;
        if (bloodType.equals("A")) {
            result = "だいきちー";
        } else if (bloodType.equals("B")) {
            result = "ちょうだいきちー";
        } else if (bloodType.equals("O")) {
            result = "まあまあー";
        } else if (bloodType.equals("AB")) {
            result = "だいきょうー";
        } else {
            result = "そんな血液型ありません";
        }

        requestScope("result", result);
        return forward("result.jsp");
    }
}
