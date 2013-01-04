package jp.itacademy.samples.gae.controller.mc;

import jp.itacademy.samples.gae.controller.memcache.PrepareController;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class PrepareControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/mc/prepare");
        PrepareController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/mc/prepare.jsp"));
    }
}
