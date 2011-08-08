package org.junit.contrib.emf;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Test;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class EmfRegistriesRuleOnTestFailureTest {

	@Test
	public void test() throws Throwable {
		try {
			EmfRegistryRule rule = new EmfRegistryRule ();
			rule.apply (new Statement () {

				@Override
				public void evaluate() throws Throwable {
					throw new RuntimeException ();
				}

			}, new FrameworkMethod (getClass ().getMethods ()[0]), this).evaluate ();
		} catch (RuntimeException e) {
			assertFalse (Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap ().containsKey ("ecore"));
			return;
		}
		assertTrue (false);
	}
}
