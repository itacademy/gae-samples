package jp.itacademy.samples.gae.controller.images;

import org.slim3.controller.Navigation;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesService.OutputEncoding;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;

public class HorizontalFlipController extends BaseController {

    @Override
    public Navigation run() throws Exception {

        Image image = ImagesServiceFactory.makeImage(loadGaeImage());
        Transform transform = ImagesServiceFactory.makeHorizontalFlip();

        ImagesService service = ImagesServiceFactory.getImagesService();
        service.applyTransform(transform, image, OutputEncoding.PNG);

        return binary(image.getImageData(), "image/png");
    }
}
