package org.junit.contrib.emf;

import org.junit.contrib.emf.EmfRegistryUtils.EmfRegistryState;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class EmfRegistryRule implements MethodRule {

	boolean initializeDefaults = true;

	public EmfRegistryRule() {
	}

	public Statement apply(final Statement base, FrameworkMethod method, Object target) {
		return new Statement () {

			@Override
			public void evaluate() throws Throwable {
				EmfRegistryState stateMemento = EmfRegistryUtils.copyRegistryState ();
				if (initializeDefaults)
					EmfRegistryUtils.initializeDefaults ();
				try {
					base.evaluate ();
				} finally {
					stateMemento.restore ();
				}
			}

		};
	}
}
