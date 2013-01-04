package jp.itacademy.samples.gae.controller.memcache.employee;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class List2ControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/memcache/employee/list2");
        List2Controller controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is(nullValue()));
    }
}
