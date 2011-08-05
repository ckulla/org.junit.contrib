package org.ckulla.junit.guice;

import org.junit.Test;

import com.google.inject.Inject;

@WithModules(EmptyModule.class)
public class GuiceTestRunnerTest extends GuiceTest {

	@Inject
	StringBuffer buffer;
	
	@Test
	public void testInjection () {
		assertNotNull (buffer);
		buffer.append ("test");
	}

	@Test
	public void testInjectionHappensForEachTestMethod() {
		// buffer should be a new created instance, and not affected by the "testInjection" test.
		assertEquals (0, buffer.length());
	}

}
