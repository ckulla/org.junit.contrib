package org.ckulla.junit.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class InjectorTest extends BaseTest {
	
	@UseInjector
	public Injector getInjector () {
		return Guice.createInjector(new TestModule());
	}
}