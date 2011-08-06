package org.ckulla.junit.guice;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.runner.RunWith;

public abstract class GuiceTest extends Assert {

	@Rule
	public GuiceRule guiceRule = new GuiceRule ();
}