package org.junit.contrib.guice;

import com.google.inject.AbstractModule;

public class TestModule extends AbstractModule {

	@Override
	protected void configure() {
		bind (StringBuffer.class).toInstance (new StringBuffer ("test"));
	}

}
