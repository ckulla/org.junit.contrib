package org.junit.contrib.guice;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.inject.Inject;

public abstract class BaseTest {

	@Inject
	StringBuffer buffer;

	@Test
	public void testInjection() {
		assertEquals ("test", buffer.toString ());
	}

}