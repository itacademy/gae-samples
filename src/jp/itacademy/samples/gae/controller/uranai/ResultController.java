package jp.itacademy.samples.gae.controller.uranai;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class ResultController extends Controller {

    @Override
    public Navigation run() throws Exception {

        String bloodType = asString("bloodType");

        String result;
        if (bloodType.equals("A")) {
            result = "���������[";
        } else if (bloodType.equals("B")) {
            result = "���傤���������[";
        } else if (bloodType.equals("O")) {
            result = "�܂��܂��[";
        } else if (bloodType.equals("AB")) {
            result = "�������傤�[";
        } else {
            result = "����Ȍ��t�^����܂���";
        }

        requestScope("result", result);
        return forward("result.jsp");
    }
}
