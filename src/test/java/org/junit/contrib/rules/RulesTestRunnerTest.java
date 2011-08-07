package org.junit.contrib.rules;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.contrib.easymock.EasyMockRule;
import org.junit.contrib.easymock.Mock;
import org.junit.contrib.easymock.ThisClassWillBeMocked;
import org.junit.contrib.guice.GuiceRule;
import org.junit.contrib.rules.Rules;
import org.junit.contrib.rules.RulesTestRunner;
import org.junit.runner.RunWith;

import com.google.inject.Inject;

@RunWith(RulesTestRunner.class)
@Rules({ EasyMockRule.class, GuiceRule.class })
public class RulesTestRunnerTest {

	@Inject
	StringBuffer buffer;

	@Mock
	ThisClassWillBeMocked mock;

	@Test
	public void testInject() {
		assertNotNull (buffer);
		EasyMock.replay (mock);
	}

	@Test
	public void testMock() {
		assertNotNull (mock);
		EasyMock.replay (mock);
	}

}
