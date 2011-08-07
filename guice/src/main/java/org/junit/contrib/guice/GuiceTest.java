package org.junit.contrib.guice;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.runner.RunWith;

public abstract class GuiceTest extends Assert {

	@Rule
	public GuiceRule guiceRule = new GuiceRule ();
}