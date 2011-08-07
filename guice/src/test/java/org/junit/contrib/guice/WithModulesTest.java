package org.junit.contrib.guice;

import org.junit.contrib.guice.GuiceTestRunner;
import org.junit.contrib.guice.WithModules;
import org.junit.runner.RunWith;

@WithModules({ TestModule.class })
@RunWith(GuiceTestRunner.class)
public class WithModulesTest extends BaseTest {
}