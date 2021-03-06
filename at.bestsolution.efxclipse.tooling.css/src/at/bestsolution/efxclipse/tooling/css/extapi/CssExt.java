/*******************************************************************************
 * Copyright (c) 2011 BestSolution.at and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tom Schindl<tom.schindl@bestsolution.at> - initial API and implementation
 *******************************************************************************/
package at.bestsolution.efxclipse.tooling.css.extapi;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import at.bestsolution.efxclipse.tooling.css.cssDsl.CssTok;
import at.bestsolution.efxclipse.tooling.css.cssDsl.css_property;
import at.bestsolution.efxclipse.tooling.css.cssDsl.selector;

import com.google.inject.Provider;

/**
 * @author ccaks
 *
 */
public interface CssExt {

	public List<Proposal> getPropertyProposalsForSelector(List<selector> selector);
	public List<Proposal> getValueProposalsForProperty(List<selector> selector, css_property property, List<CssTok> prefixTok, String prefixString);
	
	public String getDocumentationHeader(EObject obj);
	public String getDocumentation(EObject obj);
	
	
	public static class OsgiCssExtServiceProvider implements Provider<CssExt> {

		private CssExt instance = null;
		
		@Override
		public CssExt get() {
			if (instance == null) {
				BundleContext context = FrameworkUtil.getBundle(CssExt.class).getBundleContext();
				ServiceReference<CssExt> ref = context.getServiceReference(CssExt.class);
				instance = context.getService(ref);
			}
			return instance;
		}
		
	}
}
