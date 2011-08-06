package org.ckulla.junit.guice;

import org.junit.runner.RunWith;

@WithModules({ TestModule.class })
@RunWith(GuiceTestRunner.class)
public class WithModulesTest extends BaseTest {
}