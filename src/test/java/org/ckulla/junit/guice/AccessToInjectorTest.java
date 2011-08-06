package org.ckulla.junit.guice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Rule;
import org.junit.Test;

public class AccessToInjectorTest {

	@Rule
	public GuiceRule guice = new GuiceRule (new TestModule ());

	@Test
	public void testGetInjector() {
		assertNotNull (guice.getInjector ());
	}

	@Test
	public void testGetInstance() {
		assertEquals ("test", guice.getInstance (StringBuffer.class).toString ());
	}

}
