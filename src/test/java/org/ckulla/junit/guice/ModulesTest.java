package org.ckulla.junit.guice;

import java.util.List;

import com.google.inject.Module;

public class ModulesTest extends BaseTest {
	
	@Modules
	public List<Module> modules () {
		return Lists.newArrayList((Module) new TestModule());
	}
}