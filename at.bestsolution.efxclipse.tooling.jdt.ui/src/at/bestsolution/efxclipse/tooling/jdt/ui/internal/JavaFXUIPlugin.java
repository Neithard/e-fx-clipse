package at.bestsolution.efxclipse.tooling.jdt.ui.internal;

import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class JavaFXUIPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "at.bestsolution.efxclipse.tooling.jdt.ui"; //$NON-NLS-1$

	// The shared instance
	private static JavaFXUIPlugin plugin;
	
	public static String GROUP_ICON = "GROUP_ICON";
	public static String KEY_ICON = "KEY_ICON";
	public static String ALPHASORT_ICON = "ALPHASORT_ICON";
	public static String COLLAPSE_ICON = "COLLAPSE_ICON";
	public static String HIERACHICAL_ICON = "HIERACHICAL_ICON";
	
	/**
	 * The constructor
	 */
	public JavaFXUIPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		getImageRegistry().put(GROUP_ICON, ImageDescriptor.createFromURL(new URL("platform:/plugin/"+PLUGIN_ID+"/icons/ftr_jar_obj.gif")));
		getImageRegistry().put(KEY_ICON, ImageDescriptor.createFromURL(new URL("platform:/plugin/"+PLUGIN_ID+"/icons/default_co.gif")));
		getImageRegistry().put(ALPHASORT_ICON, ImageDescriptor.createFromURL(new URL("platform:/plugin/"+PLUGIN_ID+"/icons/alphab_sort_co.gif")));
		getImageRegistry().put(COLLAPSE_ICON, ImageDescriptor.createFromURL(new URL("platform:/plugin/"+PLUGIN_ID+"/icons/collapseall.gif")));
		getImageRegistry().put(HIERACHICAL_ICON, ImageDescriptor.createFromURL(new URL("platform:/plugin/"+PLUGIN_ID+"/icons/hierarchicalLayout.gif")));
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static JavaFXUIPlugin getDefault() {
		return plugin;
	}

}
