package org.junit.contrib.rules;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RulesTestRunner.class)
@Rules({ IncrementFieldValueRule.class })
public class BaseTest {

	public int i; // This field will be set by the SetFieldRule

	@Test
	public void dummyTest() {

	}
}
