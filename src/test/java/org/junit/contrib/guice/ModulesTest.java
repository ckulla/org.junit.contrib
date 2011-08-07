package org.junit.contrib.guice;

import org.junit.Rule;
import org.junit.contrib.guice.GuiceRule;

public class ModulesTest extends BaseTest {

	@Rule
	public GuiceRule guiceRule = new GuiceRule (new TestModule ());
}
