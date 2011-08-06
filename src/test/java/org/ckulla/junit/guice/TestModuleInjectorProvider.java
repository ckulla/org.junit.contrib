package org.ckulla.junit.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class TestModuleInjectorProvider implements InjectorProvider {

	public Injector get() {
		return Guice.createInjector (new TestModule ());
	}

}
