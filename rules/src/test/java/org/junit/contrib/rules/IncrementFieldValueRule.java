package org.junit.contrib.rules;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class IncrementFieldValueRule implements MethodRule {

	public Statement apply(Statement base, FrameworkMethod method, Object target) {
		((BaseTest) target).i++;
		return base;
	}

}
