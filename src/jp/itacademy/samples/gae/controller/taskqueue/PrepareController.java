package jp.itacademy.samples.gae.controller.taskqueue;

import java.util.ArrayList;
import java.util.List;

import jp.itacademy.samples.gae.model.Item;
import jp.itacademy.samples.gae.model.SaleItem;
import jp.itacademy.samples.gae.model.Shop;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

public class PrepareController extends Controller {

    @Override
    public Navigation run() throws Exception {

        Item item1 = new Item(1, "‚â‚­‚»‚¤", 8);
        Item item2 = new Item(2, "‚Ç‚­‚¯‚µ‚»‚¤", 10);
        Item item3 = new Item(3, "‚¹‚¢‚·‚¢", 20);
        Item item4 = new Item(4, "‚Ü‚ñ‚°‚Â‚»‚¤", 30);

        Shop shop = new Shop(1, "ƒiƒ“ƒgƒJ‘º‚Ì“¹‹ï‰®");

        List<Object> models = new ArrayList<Object>();
        models.add(item1);
        models.add(item2);
        models.add(item3);
        models.add(item4);
        models.add(shop);
        models.add(new SaleItem(shop, item1));
        models.add(new SaleItem(shop, item2));
        models.add(new SaleItem(shop, item3));
        Datastore.put(models);

        return forward("prepare.jsp");
    }
}
