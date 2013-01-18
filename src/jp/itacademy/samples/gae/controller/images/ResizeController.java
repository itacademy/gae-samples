package jp.itacademy.samples.gae.controller.images;

import javax.servlet.http.HttpServletResponse;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesService.OutputEncoding;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;

public class ResizeController extends AbstractImagesController {

    @Override
    public Navigation run() throws Exception {

        Validators v = new Validators(request);
        v.add("rate", v.required(), v.integerType());
        if (!v.validate()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        int rate = asInteger("rate");

        Image image =
            ImagesServiceFactory.makeImage(loadImage("/images/gae.png"));

        int newWidth = image.getWidth() * rate / 100;
        int newHeight = image.getHeight() * rate / 100;
        Transform transform =
            ImagesServiceFactory.makeResize(newWidth, newHeight);

        ImagesService service = ImagesServiceFactory.getImagesService();
        service.applyTransform(transform, image, OutputEncoding.PNG);

        return binary(image.getImageData(), "image/png");
    }

}
