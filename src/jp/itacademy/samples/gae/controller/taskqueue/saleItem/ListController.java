package jp.itacademy.samples.gae.controller.taskqueue.saleItem;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jp.itacademy.samples.gae.meta.SaleItemMeta;
import jp.itacademy.samples.gae.model.SaleItem;
import jp.itacademy.samples.gae.model.Shop;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;

public class ListController extends Controller {

    @Override
    public Navigation run() throws Exception {

        Validators v = new Validators(request);
        v.add("shopId", v.required(), v.longType());
        if (!v.validate()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        long shopId = asLong("shopId");
        Key shopKey = Datastore.createKey(Shop.class, shopId);

        SaleItemMeta m = SaleItemMeta.get();
        List<SaleItem> saleItems =
            Datastore.query(m).filter(m.shopKey.equal(shopKey)).asList();
        requestScope("saleItems", saleItems);

        return forward("list.jsp");
    }
}
