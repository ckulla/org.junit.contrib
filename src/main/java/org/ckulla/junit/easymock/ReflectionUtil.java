package org.ckulla.junit.easymock;

import java.lang.annotation.Annotation;

public class ReflectionUtil {

	public boolean hasAnnotation(Class<?> clazz, Annotation[] annotations) {
		for (Annotation a : annotations) {
			if (clazz.isInstance(a))
				return true;
		}
		return false;
	}

}
