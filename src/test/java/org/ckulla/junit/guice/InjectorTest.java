package org.ckulla.junit.guice;

import org.junit.Rule;

import com.google.inject.Guice;

public class InjectorTest extends BaseTest {

	@Rule
	public GuiceRule guiceRule = new GuiceRule (Guice.createInjector(new TestModule()));
	
}
