package jp.itacademy.samples.gae.controller.images;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public abstract class AbstractImagesController extends Controller {

    protected byte[] loadImage(String imageName) throws IOException {
        InputStream in =
            AbstractImagesController.class.getResourceAsStream(imageName);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[4096];
        int readBytes = 0;
        while ((readBytes = in.read(buf)) != -1) {
            out.write(buf, 0, readBytes);
        }
        return out.toByteArray();
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
