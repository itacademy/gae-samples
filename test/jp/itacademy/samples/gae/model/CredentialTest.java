package jp.itacademy.samples.gae.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CredentialTest extends AppEngineTestCase {

    private Credential model = new Credential();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
