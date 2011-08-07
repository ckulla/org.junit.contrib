package org.junit.contrib.guice;

import org.junit.Rule;
import org.junit.contrib.guice.GuiceRule;

import com.google.inject.Guice;

public class InjectorTest extends BaseTest {

	@Rule
	public GuiceRule guiceRule = new GuiceRule (Guice.createInjector (new TestModule ()));

}
