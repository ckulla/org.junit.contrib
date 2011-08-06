package org.ckulla.junit.guice;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.ckulla.junit.easymock.ReflectionUtil;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class GuiceRule implements MethodRule {

	ReflectionUtil reflectionUtil = new ReflectionUtil ();
	
	Injector injector;

	public GuiceRule () {
	}

	public GuiceRule (Injector injector) {
		this.injector = injector;
	}

	public GuiceRule (Module...modules) {
		this.injector = Guice.createInjector(modules);		
	}

	public Statement apply(final Statement base, FrameworkMethod method, final Object target) {
		return new Statement () {

			@Override
			public void evaluate() throws Throwable {
				if (injector == null)
					injector = createInjector (target);
				if (injector != null) {
					injector.injectMembers(target);
				} else {
					// FIXME: report error
				}
				base.evaluate();
			}
			
		};
	}

	protected Injector createInjector(Object o) {
		if (getWithModulesAnnotation(o.getClass()) != null) {
			return getInjectorFromWithModulesAnnotation(o.getClass());
		}
		if (getInjectWithAnnotation(o.getClass()) != null) {
			return getInjectorFormInjectWithAnnotation(o.getClass());
		}
		return getDefaultInjector();
	}

	private InjectWith getInjectWithAnnotation(Class<?> clazz) {
		return findInherited(clazz, InjectWith.class);
	}

	private Injector getInjectorFromWithModulesAnnotation(Class<?> clazz) {
		List<Module> modules = Lists.newArrayList();
		for (Class<? extends Module> moduleClass : getWithModulesAnnotation(clazz).value()) {
			try {
				modules.add (moduleClass.newInstance());
			} catch (InstantiationException e) {
				throw new RuntimeException (e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException (e);
			}				
		}
		return Guice.createInjector(modules);
	}

	private WithModules getWithModulesAnnotation(Class<?> clazz) {
		return findInherited(clazz, WithModules.class);
	}

	private Injector getInjectorFormInjectWithAnnotation(Class<?> clazz) {
		try {
			return getInjectWithAnnotation(clazz).value().newInstance().get();
		} catch (InstantiationException e) {
			throw new RuntimeException (e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException (e);
		}
	}

	protected Injector getDefaultInjector() {
		return Guice.createInjector (com.google.inject.util.Modules.EMPTY_MODULE);
	}

	public static <A extends Annotation> A findInherited(Class<?> clazz,
			Class<A> annotationType) {
		A annotation = clazz.getAnnotation(annotationType);
		if (annotation != null) {
			return annotation;
		}
		for (Class<?> ifc : clazz.getInterfaces()) {
			annotation = findInherited(ifc, annotationType);
			if (annotation != null) {
				return annotation;
			}
		}
		Class<?> superClass = clazz.getSuperclass();
		if (superClass == null || superClass == Object.class) {
			return null;
		}
		return findInherited(superClass, annotationType);
	}
	
}
