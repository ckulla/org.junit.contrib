package org.ckulla.junit.easymock;

import org.ckulla.junit.rules.RulesTestRunner;
import org.junit.runners.model.InitializationError;

public class EasyMockTestRunner extends RulesTestRunner {

	public EasyMockTestRunner(Class<?> clazz) throws InitializationError {
		super(clazz, new EasyMockRule ());
	}

}
