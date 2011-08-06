package org.ckulla.junit.guice;

import org.junit.Rule;

public class ModulesTest extends BaseTest {

	@Rule
	public GuiceRule guiceRule = new GuiceRule (new TestModule());
}
