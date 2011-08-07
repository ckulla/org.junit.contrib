package org.junit.contrib.emf;

import org.junit.contrib.emf.GlobalRegistries.GlobalStateMemento;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class EmfRegistryRule implements MethodRule {

	boolean initializeDefaults = true;

	public EmfRegistryRule() {
	}

	public EmfRegistryRule(boolean initializeDefaults) {
		this.initializeDefaults = initializeDefaults;
	}

	public Statement apply(final Statement base, FrameworkMethod method, Object target) {
		return new Statement () {

			@Override
			public void evaluate() throws Throwable {
				GlobalStateMemento stateMemento = GlobalRegistries.makeCopyOfGlobalState ();
				if (initializeDefaults)
					GlobalRegistries.initializeDefaults ();
				base.evaluate ();
				stateMemento.restoreGlobalState ();
			}

		};
	}
}
