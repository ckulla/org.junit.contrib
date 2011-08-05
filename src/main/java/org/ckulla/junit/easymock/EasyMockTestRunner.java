package org.ckulla.junit.easymock;

import java.lang.reflect.Field;
import java.util.List;

import org.ckulla.junit.guice.GuiceTestRunner;
import org.ckulla.junit.guice.Lists;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.internal.runners.statements.RunAfters;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

public class EasyMockTestRunner extends GuiceTestRunner {

	public EasyMockTestRunner(Class<?> clazz) throws InitializationError {
		super(clazz);
	}

	@Override
	protected Object createTest() throws Exception {
		Object o = super.createTest();
		createMocks(o);
		return o;
	}

	private List<Field> getMockFields (Class<?> clazz) {
		List<Field> fields = Lists.newArrayList();
		for (Field f : clazz.getDeclaredFields()) {
			if (hasAnnotation(Mock.class, f.getAnnotations())) {
				fields.add (f);
			}
		}
		return fields;
	}
	
	private void createMocks(Object o) {
		for (Field f : getMockFields(o.getClass())) {
			try {
				Object mock = EasyMock.createMock(f.getType());
				f.set(o, mock);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	protected Statement withAfters(final FrameworkMethod method, final Object target, final Statement statement) {
		return new Statement () {

			@Override
			public void evaluate() throws Throwable {
				EasyMockTestRunner.super.withAfters (method,target,statement).evaluate();
				verifyMocks (target);
			}

			private void verifyMocks(Object o) throws IllegalArgumentException, IllegalAccessException {
				for (Field f : getMockFields(o.getClass())) {
					EasyMock.verify (f.get(o));
				}
			}			
		};		
	}
}
