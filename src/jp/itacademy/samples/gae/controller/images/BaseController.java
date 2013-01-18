package jp.itacademy.samples.gae.controller.images;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public abstract class BaseController extends Controller {

    public static byte[] loadImage(String imageName) throws IOException {
        InputStream in = ResizeController.class.getResourceAsStream(imageName);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[4096];
        int readBytes = 0;
        while ((readBytes = in.read(buf)) != -1) {
            out.write(buf, 0, readBytes);
        }
        return out.toByteArray();
    }

    public static byte[] loadGaeImage() throws IOException {
        return loadImage("/images/gae.png");
    }

    public static byte[] loadGirlImage() throws IOException {
        return loadImage("/images/girl.png");
    }

    public static byte[] loadDukeImage() throws IOException {
        return loadImage("/images/duke.png");
    }

    protected Navigation binary(byte[] data, String contentType)
            throws IOException {
        response.setContentType(contentType);
        OutputStream out = response.getOutputStream();
        out.write(data);
        out.flush();
        return null;
    }

}
