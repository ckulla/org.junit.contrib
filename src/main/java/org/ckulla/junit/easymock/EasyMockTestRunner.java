package org.ckulla.junit.easymock;

import java.lang.reflect.Field;
import java.util.List;

import org.ckulla.junit.guice.GuiceTestRunner;
import org.ckulla.junit.guice.Lists;
import org.easymock.EasyMock;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

public class EasyMockTestRunner extends GuiceTestRunner {

	List<Object> mocks;

	public EasyMockTestRunner(Class<?> clazz) throws InitializationError {
		super(clazz);
	}

	@Override
	protected Object createTest() throws Exception {
		
		Object o = super.createTest();
		createMocks(o);
		return o;
	}

	private void createMocks(Object o) {
		mocks = Lists.newArrayList();
		for (Field f : o.getClass().getDeclaredFields()) {
			if (hasAnnotation(Mock.class, f.getAnnotations())) {
				try {
					Object mock = EasyMock.createMock(f.getType());
					mocks.add(mock);
					f.set(o, mock);
				} catch (IllegalArgumentException e) {
					throw new RuntimeException(e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	@Override
	protected Statement methodBlock(final FrameworkMethod method) {
		return new Statement () {

			@Override
			public void evaluate() throws Throwable {
				EasyMockTestRunner.super.methodBlock(method).evaluate();
				verifyMocks ();
			}

			private void verifyMocks() {
				for (Object o : mocks) {
					EasyMock.verify (o);
				}
				mocks = null;
			}
			
		};		
	}
}
