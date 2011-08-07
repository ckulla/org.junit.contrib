package org.junit.contrib.rules;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RulesTestRunner.class)
@Rules({ SetFieldRule.class })
public class RulesTestRunnerTest {

	public int i; // This field will be set by the SetFieldRule

	@Test
	public void testInject() {
		assertEquals (42, i);
	}

}
