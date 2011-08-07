package org.junit.contrib.easymock;

import org.junit.contrib.rules.RulesTestRunner;
import org.junit.runners.model.InitializationError;

public class EasyMockTestRunner extends RulesTestRunner {

	public EasyMockTestRunner(Class<?> clazz) throws InitializationError {
		super (clazz, new EasyMockRule ());
	}

}
