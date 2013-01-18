package jp.itacademy.samples.gae.controller.images;

import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Navigation;

import com.google.appengine.api.images.Composite;
import com.google.appengine.api.images.Composite.Anchor;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesService.OutputEncoding;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;

public class CompositeController extends AbstractImagesController {

    @Override
    public Navigation run() throws Exception {

        Image girl =
            ImagesServiceFactory.makeImage(loadImage("/images/girl.jpg"));
        Image duke =
            ImagesServiceFactory.makeImage(loadImage("/images/duke.png"));

        ImagesService serv = ImagesServiceFactory.getImagesService();

        // ÉfÉÖÅ[ÉNÇèkè¨Ç∑ÇÈ
        Transform transform = ImagesServiceFactory.makeResize(132, 102);
        serv.applyTransform(transform, duke);

        List<Composite> composites = new ArrayList<Composite>();
        composites.add(ImagesServiceFactory.makeComposite(
            girl,
            0,
            0,
            1,
            Anchor.TOP_LEFT));

        int distance = 160;
        for (int i = 0; i < 9; i++) {
            composites.add(ImagesServiceFactory.makeComposite(
                duke,
                10 + (int) (distance * (1 - Math.pow(0.75, i))),
                20,
                0.1f * (i + 1),
                Anchor.TOP_LEFT));
        }

        Image merged =
            serv.composite(
                composites,
                girl.getWidth(),
                girl.getHeight(),
                0xFFFFFFFFL,
                OutputEncoding.PNG);

        return binary(merged.getImageData(), "image/png");
    }
}
