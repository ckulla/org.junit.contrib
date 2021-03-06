package org.junit.contrib.easymock;

import java.lang.reflect.Field;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.contrib.rules.Lists;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class EasyMockRule implements MethodRule {

	ReflectionUtil reflectionUtil = new ReflectionUtil ();

	public Statement apply(final Statement base, FrameworkMethod method, final Object target) {
		return new Statement () {

			@Override
			public void evaluate() throws Throwable {
				createMocks (target);
				base.evaluate ();
				verifyMocks (target);
			}

		};
	}

	private List<Field> getMockFields(Class<?> clazz) {
		List<Field> fields = Lists.newArrayList ();
		for (Field f : clazz.getDeclaredFields ()) {
			if (reflectionUtil.hasAnnotation (Mock.class, f.getAnnotations ())) {
				fields.add (f);
			}
		}
		return fields;
	}

	private void createMocks(Object o) {
		for (Field field : getMockFields (o.getClass ())) {
			try {
				Object mock = EasyMock.createMock (field.getType ());
				field.setAccessible (true);
				field.set (o, mock);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException (e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException (e);
			} catch (SecurityException e) {
				throw new RuntimeException (e);
			}
		}
	}

	private void verifyMocks(Object o) throws IllegalArgumentException, IllegalAccessException {
		for (Field f : getMockFields (o.getClass ())) {
			f.setAccessible (true);
			EasyMock.verify (f.get (o));
		}
	}

}
