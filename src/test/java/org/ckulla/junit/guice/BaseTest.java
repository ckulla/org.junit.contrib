package org.ckulla.junit.guice;

import org.junit.Test;

import com.google.inject.Inject;

public abstract class BaseTest extends GuiceTest {

	@Inject
	StringBuffer buffer;
	
	@Test
	public void testInjection () {
		assertEquals ("test", buffer.toString());
	}

}