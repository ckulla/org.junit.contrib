package org.junit.contrib.guice;

import org.junit.contrib.guice.InjectorProvider;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class TestModuleInjectorProvider implements InjectorProvider {

	public Injector get() {
		return Guice.createInjector (new TestModule ());
	}

}
