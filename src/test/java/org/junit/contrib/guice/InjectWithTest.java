package org.junit.contrib.guice;

import org.junit.contrib.guice.GuiceTestRunner;
import org.junit.contrib.guice.InjectWith;
import org.junit.runner.RunWith;

@RunWith(GuiceTestRunner.class)
@InjectWith(TestModuleInjectorProvider.class)
public class InjectWithTest extends BaseTest {
}
