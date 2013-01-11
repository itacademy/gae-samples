package jp.itacademy.samples.gae.controller.taskqueue.sale;

import jp.itacademy.samples.gae.controller.taskqueue.saleItem.ListController;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ListControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/taskqueue/sale/list");
        ListController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/taskqueue/sale/list.jsp"));
    }
}
