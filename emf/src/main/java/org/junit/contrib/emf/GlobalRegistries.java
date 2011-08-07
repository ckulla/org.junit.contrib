package org.junit.contrib.emf;

import java.util.HashMap;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

/**
 * 
 * Utility for tests to avoid being based on and doing any side effects to the
 * global registries from EMF.
 * 
 * @author Sven Efftinge - Initial contribution and API
 */
public class GlobalRegistries {

	public static class GlobalStateMemento {
		private HashMap<EPackage, Object> validatorReg;
		private HashMap<String, Object> epackageReg;
		private HashMap<String, Object> protocolToFactoryMap;
		private HashMap<String, Object> extensionToFactoryMap;
		private HashMap<String, Object> contentTypeIdentifierToFactoryMap;

		public void restoreGlobalState() {
			clearGlobalRegistries ();
			EValidator.Registry.INSTANCE.putAll (validatorReg);
			EPackage.Registry.INSTANCE.putAll (epackageReg);

			Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap ().putAll (protocolToFactoryMap);
			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap ().putAll (extensionToFactoryMap);
			Resource.Factory.Registry.INSTANCE.getContentTypeToFactoryMap ().putAll (contentTypeIdentifierToFactoryMap);
		}
	}

	public static GlobalStateMemento makeCopyOfGlobalState() {
		GlobalStateMemento memento = new GlobalStateMemento ();
		memento.validatorReg = new HashMap<EPackage, Object> (EValidator.Registry.INSTANCE);
		memento.epackageReg = new HashMap<String, Object> (EPackage.Registry.INSTANCE);
		memento.protocolToFactoryMap = new HashMap<String, Object> (
					Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap ());
		memento.extensionToFactoryMap = new HashMap<String, Object> (
					Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap ());
		memento.contentTypeIdentifierToFactoryMap = new HashMap<String, Object> (
					Resource.Factory.Registry.INSTANCE.getContentTypeToFactoryMap ());
		return memento;
	}

	public static void clearGlobalRegistries() {
		EValidator.Registry.INSTANCE.clear ();
		EPackage.Registry.INSTANCE.clear ();
		Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap ().clear ();
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap ().clear ();
		Resource.Factory.Registry.INSTANCE.getContentTypeToFactoryMap ().clear ();

		initializeDefaults ();
	}

	public static void initializeDefaults() {
		// EMF Standalone setup
		if (!Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap ().containsKey ("ecore"))
			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap ().put ("ecore", new EcoreResourceFactoryImpl ());
		if (!Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap ().containsKey ("xmi"))
			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap ().put ("xmi", new XMIResourceFactoryImpl ());
		if (!EPackage.Registry.INSTANCE.containsKey (EcorePackage.eNS_URI))
			EPackage.Registry.INSTANCE.put (EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
	}

}
