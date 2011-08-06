package org.ckulla.junit.easymock;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Rule;
import org.junit.Test;

public class EasyMockRuleTest {
	
	@Rule
	public EasyMockRule easyMockRule = new EasyMockRule ();

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
