package org.junit.contrib.eclipse.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class WorkbenchUtil implements MethodRule {

	public Statement apply(final Statement base, FrameworkMethod method, Object target) {
		return new Statement () {

			@Override
			public void evaluate() throws Throwable {
				closeWelcomePage ();
				try {
					base.evaluate ();
				} finally {
					closeAllEditors ();
					closeAllViews ();
				}
			}
		};
	}

	public IWorkbenchPage getActivePage() {
		return getWorkbenchWindow ().getActivePage ();
	}

	public IWorkbenchWindow getWorkbenchWindow() {
		return getWorkbench ().getActiveWorkbenchWindow ();
	}

	public IWorkbench getWorkbench() {
		return PlatformUI.getWorkbench ();
	}

	public IViewPart openView(String viewId) throws Exception {
		IViewPart viewPart = PlatformUI.getWorkbench ().getActiveWorkbenchWindow ().getActivePage ().showView (viewId);
		if (viewPart == null) {
			org.junit.Assert.fail ("Could not open a view of id: " + viewId);
		}
		return viewPart;
	}

	public void closeAllEditors() {
		getActivePage ().closeAllEditors (false);
	}

	public void closeAllViews() throws Exception {
		IViewReference[] viewRefs = getActivePage ().getViewReferences ();
		for (IViewReference viewRef : viewRefs) {
			getActivePage ().hideView (viewRef.getView (false));
		}
	}

	public void closeWelcomePage() throws InterruptedException {
		if (PlatformUI.getWorkbench ().getIntroManager ().getIntro () != null) {
			PlatformUI.getWorkbench ().getIntroManager ().closeIntro (getWorkbench ().getIntroManager ().getIntro ());
		}
	}

	public void sleep(long i) throws InterruptedException {
		Display displ = Display.getCurrent ();
		if (displ != null) {
			long timeToGo = System.currentTimeMillis () + i;
			while (System.currentTimeMillis () < timeToGo) {
				if (!displ.readAndDispatch ()) {
					displ.sleep ();
				}
			}
			displ.update ();
		} else {
			Thread.sleep (i);
		}

	}
}
