package org.ckulla.junit.guice;

import org.ckulla.junit.rules.RulesTestRunner;
import org.junit.runners.model.InitializationError;

public class GuiceTestRunner extends RulesTestRunner {

	public GuiceTestRunner(Class<?> klass) throws InitializationError {
		super (klass, new GuiceRule ());
	}

}
