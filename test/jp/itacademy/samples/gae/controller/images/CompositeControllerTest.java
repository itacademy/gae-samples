package jp.itacademy.samples.gae.controller.images;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CompositeControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/images/composite");
        CompositeController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is(nullValue()));
    }
}
