package org.junit.contrib.emf;

import static org.junit.Assert.assertTrue;

import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Test;
import org.junit.contrib.rules.Rules;
import org.junit.contrib.rules.RulesTestRunner;
import org.junit.runner.RunWith;

@RunWith(RulesTestRunner.class)
@Rules({ EmfRegistryRule.class })
public class EmfRegistiresRuleTest {

	@Test
	public void testEcoreIsRegistered() {
		assertTrue (Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap ().containsKey ("ecore"));
	}
}
