package org.ckulla.junit.guice;

import org.junit.Test;

import com.google.inject.Inject;

public class GuiceTestRunnerTest extends GuiceTest {

	@Inject
	StringBuffer buffer;

	@Test
	public void testInjection() {
		assertNotNull (buffer);
	}

}
