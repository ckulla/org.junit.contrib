package org.ckulla.junit.easymock;

import org.junit.internal.runners.statements.InvokeMethod;
import org.junit.rules.MethodRule;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

public class EasyMockTestRunner extends BlockJUnit4ClassRunner {

	public EasyMockTestRunner(Class<?> clazz) throws InitializationError {
		super(clazz);
	}

	@Override
	protected Statement methodInvoker(FrameworkMethod method, Object test) {
		MethodRule rule = new EasyMockRule();
		return rule.apply(new InvokeMethod(method, test), method, test);
	}
	
}
