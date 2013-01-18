package jp.itacademy.samples.gae.controller.images;

import javax.servlet.http.HttpServletResponse;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;
import com.google.appengine.api.images.ImagesService.OutputEncoding;

public class RotateController extends BaseController {

    @Override
    public Navigation run() throws Exception {

        Validators v = new Validators(request);
        v.add("degree", v.required(), v.integerType());
        if (!v.validate()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        int degree = asInteger("degree");

        Image image = ImagesServiceFactory.makeImage(loadGaeImage());
        Transform transform = ImagesServiceFactory.makeRotate(degree);

        ImagesService service = ImagesServiceFactory.getImagesService();
        service.applyTransform(transform, image, OutputEncoding.PNG);

        return binary(image.getImageData(), "image/png");
    }
}
