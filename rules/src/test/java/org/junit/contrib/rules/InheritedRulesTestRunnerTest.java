package org.junit.contrib.rules;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

@Rules({ FieldValueTimes2Rule.class })
public class InheritedRulesTestRunnerTest extends BaseTest {

	/**
	 * This test checks the inheritance of rules and their execution order.
	 * 
	 * The IncrementFieldValueRule from BaseTest is executed first (i=1), then
	 * FieldValueTimes2Rule is executed, i=2 then. If the order would be changed,
	 * i would be equal to 1. Or if the inherited rule wouldn't be executed, i
	 * would be equal to 0.
	 */
	@Test
	public void test() {
		assertEquals (2, i);
	}

}
