package org.junit.contrib.easymock;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.contrib.easymock.EasyMockTestRunner;
import org.junit.contrib.easymock.Mock;
import org.junit.runner.RunWith;

@RunWith(EasyMockTestRunner.class)
public class EasyMockTestRunnerTest {

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
