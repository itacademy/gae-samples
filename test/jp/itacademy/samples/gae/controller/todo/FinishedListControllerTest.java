package jp.itacademy.samples.gae.controller.todo;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class FinishedListControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/todo/finishedList");
        FinishedListController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/todo/finishedList.jsp"));
    }
}
