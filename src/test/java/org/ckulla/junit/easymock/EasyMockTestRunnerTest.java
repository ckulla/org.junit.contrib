package org.ckulla.junit.easymock;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;

@RunWith(EasyMockTestRunner.class)
public class EasyMockTestRunnerTest  {

	@Mock
	ThisClassWillBeMocked mock;
	
	@Test
	public void testCreateMocks () {
		assertNotNull (mock);
		EasyMock.replay (mock);
	}

	@Test
	public void testCheckBehaviour () {
		mock.foo ();
		EasyMock.replay (mock);
		mock.foo ();
	}

	@Test(expected=java.lang.AssertionError.class)
	public void testCheckBehaviourFailing () {
		EasyMock.replay (mock);
		mock.foo ();
	}

}
