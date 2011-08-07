package org.junit.contrib.easymock;

import java.lang.annotation.Annotation;

public class ReflectionUtil {

	public boolean hasAnnotation(Class<?> clazz, Annotation[] annotations) {
		for (Annotation a : annotations) {
			if (clazz.isInstance (a))
				return true;
		}
		return false;
	}

	public <A extends Annotation> A findInherited(Class<?> clazz, Class<A> annotationType) {
		A annotation = clazz.getAnnotation (annotationType);
		if (annotation != null) {
			return annotation;
		}
		for (Class<?> ifc : clazz.getInterfaces ()) {
			annotation = findInherited (ifc, annotationType);
			if (annotation != null) {
				return annotation;
			}
		}
		Class<?> superClass = clazz.getSuperclass ();
		if (superClass == null || superClass == Object.class) {
			return null;
		}
		return findInherited (superClass, annotationType);
	}

}
