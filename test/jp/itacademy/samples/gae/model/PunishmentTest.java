package jp.itacademy.samples.gae.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class PunishmentTest extends AppEngineTestCase {

    private Punishment model = new Punishment();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
