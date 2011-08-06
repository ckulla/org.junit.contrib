package org.ckulla.junit.guice;

import org.junit.internal.runners.statements.InvokeMethod;
import org.junit.rules.MethodRule;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

public class GuiceTestRunner extends BlockJUnit4ClassRunner {

	public GuiceTestRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	protected Statement methodInvoker(FrameworkMethod method, Object test) {
		MethodRule rule = new GuiceRule();
		return rule.apply(new InvokeMethod(method, test), method, test);
	}

}
