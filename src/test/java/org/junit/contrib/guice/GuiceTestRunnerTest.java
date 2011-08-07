package org.junit.contrib.guice;

import org.junit.Test;
import org.junit.contrib.guice.GuiceTest;

import com.google.inject.Inject;

public class GuiceTestRunnerTest extends GuiceTest {

	@Inject
	StringBuffer buffer;

	@Test
	public void testInjection() {
		assertNotNull (buffer);
	}

}
