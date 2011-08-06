package org.ckulla.junit.guice;

import org.junit.runner.RunWith;

@RunWith(GuiceTestRunner.class)
@InjectWith(TestModuleInjectorProvider.class)
public class InjectWithTest extends BaseTest {
}
