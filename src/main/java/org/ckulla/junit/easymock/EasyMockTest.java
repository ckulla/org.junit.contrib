package org.ckulla.junit.easymock;

import org.junit.Assert;
import org.junit.Rule;

public abstract class EasyMockTest extends Assert {

	@Rule
	public EasyMockRule easyMockRule = new EasyMockRule ();

}