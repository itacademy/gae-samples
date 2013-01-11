package jp.itacademy.samples.gae.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class SaleTest extends AppEngineTestCase {

    private SaleItem model = new SaleItem();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
