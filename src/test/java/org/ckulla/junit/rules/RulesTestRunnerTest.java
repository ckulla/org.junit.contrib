package org.ckulla.junit.rules;

import static org.junit.Assert.*;

import org.ckulla.junit.easymock.EasyMockRule;
import org.ckulla.junit.easymock.Mock;
import org.ckulla.junit.easymock.ThisClassWillBeMocked;
import org.ckulla.junit.guice.GuiceRule;
import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;

@RunWith (RulesTestRunner.class)
@Rules ({EasyMockRule.class, GuiceRule.class})
public class RulesTestRunnerTest {

	@Inject
	StringBuffer buffer;
	
	@Mock
	public ThisClassWillBeMocked mock;
	
	@Test
	public void testInject () {
		assertNotNull (buffer);
		EasyMock.replay(mock);
	}

	@Test
	public void testMock () {
		assertNotNull (mock);
		EasyMock.replay(mock);
	}

}
