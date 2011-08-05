package org.ckulla.junit.guice;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class GuiceTestRunner extends BlockJUnit4ClassRunner {

	public GuiceTestRunner(Class<?> clazz) throws InitializationError {
		super(clazz);
	}

	@Override
	protected Object createTest() throws Exception {
		Object instance = super.createTest();
		getInjector(instance).injectMembers(instance);
		return instance;
	}
	
	protected Injector getInjector(Object o) {
		if (getModulesMethod(o.getClass()) != null) {
			return getInjectorFromModulesMethod(o);
		}		
		if (getUseInjectorMethod(o.getClass()) != null) {
			return getInjectorFromUseInjectorMethod(o);
		}
		if (getWithModulesAnnotation(o.getClass()) != null) {
			return getInjectorFromWithModulesAnnotation(o.getClass());
		}
		if (getInjectWithAnnotation(o.getClass()) != null) {
			return getInjectorFormInjectWithAnnotation(o.getClass());
		}
		return getDefaultInjector();
	}

	private Injector getInjectorFromUseInjectorMethod(Object o) {
		try {
			return (Injector) getUseInjectorMethod(o.getClass()).invoke (o);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException (e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException (e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException (e);
		}
	}

	private Method getUseInjectorMethod(Class<? extends Object> clazz) {
		for (Method m : clazz.getMethods()) {
			if (hasAnnotation (UseInjector.class, m.getAnnotations())) {
				// FIXME: check return type
				return m;
			}
		}
		return null;
	}

	private Injector getInjectorFromModulesMethod(Object o) {
		try {
			return Guice.createInjector((Iterable<Module>) getModulesMethod(o.getClass()).invoke (o));
		} catch (IllegalArgumentException e) {
			throw new RuntimeException (e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException (e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException (e);
		}
	}

	private Method getModulesMethod(Class<?> clazz) {
		for (Method m : clazz.getMethods()) {
			if (hasAnnotation (Modules.class, m.getAnnotations())) {
				// FIXME: check return type
				return m;
			}
		}
		return null;
	}

	protected boolean hasAnnotation(Class<?> clazz, Annotation[] annotations) {
		for (Annotation a : annotations) {
			if (clazz.isInstance(a))
				return true;
		}
		return false;
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
