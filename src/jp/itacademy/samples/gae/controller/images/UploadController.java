package jp.itacademy.samples.gae.controller.images;

import jp.itacademy.samples.gae.model.ImageFile;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.upload.FileItem;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;

public class UploadController extends Controller {

    @Override
    public Navigation run() throws Exception {

        FileItem file = requestScope("imageFile");

        int resize = 100;
        try {
            resize = asInteger("resize");
        } catch (Exception e) {
            // NumberFormatException, NullPointerException
            // Ç™î≠ê∂Ç∑ÇÈâ¬î\ê´Ç™Ç†ÇÈÇ™ÅAÇ«ÇøÇÁÇ‡ñ≥éãÇ∑ÇÈ
        }

        Image image = ImagesServiceFactory.makeImage(file.getData());
        int w = image.getWidth() * resize / 100;
        int h = image.getHeight() * resize / 100;
        Transform transform = ImagesServiceFactory.makeResize(w, h);
        image = ImagesServiceFactory
            .getImagesService()
            .applyTransform(transform, image);

        ImageFile imageFile = new ImageFile();
        imageFile.setFileName(file.getFileName());
        imageFile.setContentType(file.getContentType());
        imageFile.setData(image.getImageData());
        Datastore.put(imageFile);

        requestScope("imageFile", imageFile);
        return forward("upload.jsp");
    }
}
