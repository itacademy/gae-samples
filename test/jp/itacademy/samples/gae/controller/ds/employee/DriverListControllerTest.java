package jp.itacademy.samples.gae.controller.ds.employee;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class DriverListControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/ds/employee/driverList");
        DriverListController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is(nullValue()));
    }
}
