package org.junit.contrib.easymock;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.contrib.easymock.EasyMockTest;
import org.junit.contrib.easymock.Mock;

public class EasyMockTestTest extends EasyMockTest {

	@Mock
	ThisClassWillBeMocked mock;

	@Test
	public void testCreateMocks() {
		assertNotNull (mock);
		EasyMock.replay (mock);
	}

	@Test
	public void testCheckBehaviour() {
		mock.foo ();
		EasyMock.replay (mock);
		mock.foo ();
	}

	@Test(expected = java.lang.AssertionError.class)
	public void testCheckBehaviourFailing() {
		EasyMock.replay (mock);
		mock.foo ();
	}

}