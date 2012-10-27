package jp.itacademy.samples.gae.controller.contact;

import java.util.List;

import jp.itacademy.samples.gae.model.Contact;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
        List<Contact> contacts = Datastore.query(Contact.class).asList();
        requestScope("contacts", contacts);
        return forward("index.jsp");
    }
}
