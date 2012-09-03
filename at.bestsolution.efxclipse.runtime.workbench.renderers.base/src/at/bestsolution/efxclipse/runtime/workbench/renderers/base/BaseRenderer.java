/*******************************************************************************
 * Copyright (c) 2012 BestSolution.at and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tom Schindl<tom.schindl@bestsolution.at> - initial API and implementation
 *******************************************************************************/
package at.bestsolution.efxclipse.runtime.workbench.renderers.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.ui.MContext;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.MUILabel;
import org.eclipse.e4.ui.model.application.ui.advanced.MPlaceholder;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.IPresentationEngine;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import at.bestsolution.efxclipse.runtime.workbench.base.rendering.AbstractRenderer;
import at.bestsolution.efxclipse.runtime.workbench.renderers.base.widget.WPropertyChangeHandler;
import at.bestsolution.efxclipse.runtime.workbench.renderers.base.widget.WWidget;

@SuppressWarnings("restriction")
public abstract class BaseRenderer<M extends MUIElement, W extends WWidget<M>> extends AbstractRenderer<M, W> {
	private static final String RENDERING_CONTEXT_KEY = "fx.rendering.context";
	
	public static final String CONTEXT_DOM_ELEMENT = "fx.rendering.domElement";
	
	public static final String ATTRIBUTE_localizedLabel = "localizedLabel";
	public static final String ATTRIBUTE_localizedTooltip = "localizedTooltip";
	
	@Inject
	IEclipseContext _context; // The rendering context
	
	@Inject
	EModelService modelService;
	
//	boolean inContentProcessing;
//	
//	boolean inContextModification;
//	
//	boolean inUIModification;
	
	private Map<String,EAttribute> attributeMap = new HashMap<String, EAttribute>();
	
	private Map<MUIElement, Boolean> contentProcessing = new HashMap<MUIElement, Boolean>();
	private Map<MUIElement, Boolean> contextModification = new HashMap<MUIElement, Boolean>();
	private Map<MUIElement, Boolean> uiModification = new HashMap<MUIElement, Boolean>();
	
	protected boolean inContentProcessing(MUIElement element) {
		return contentProcessing.get(element) == Boolean.TRUE;
	}
	
	protected boolean inContextModification(MUIElement element) {
		return contextModification.get(element) == Boolean.TRUE;
	}
	
	protected boolean inUIModification(MUIElement element) {
		return uiModification.get(element) == Boolean.TRUE;
	}
	
	@Override
	public final W createWidget(final M element) {
		final IEclipseContext context = setupRenderingContext(element);
		
		W widget =  ContextInjectionFactory.make(getWidgetClass(), context);
		widget.setPropertyChangeHandler(new WPropertyChangeHandler<W>() {

			@Override
			public void propertyObjectChanged(WPropertyChangeEvent<W> event) {
				// There is already a modification in process
				if( inUIModification(element) || inContextModification(element) ) {
					return;
				}
				
				try {
					uiModification.put(element,Boolean.TRUE);
					
					EAttribute attribute = attributeMap.get(event.propertyname);
					EObject eo = (EObject)element;
					
					if( attribute == null ) {
						EStructuralFeature f = eo.eClass().getEStructuralFeature(event.propertyname);
						if( f instanceof EAttribute ) {
							attribute = (EAttribute) f;
							attributeMap.put(event.propertyname, attribute);
						}
					}
					
					if( attribute != null ) {
						if( attribute.getEType().getInstanceClass() == int.class ) {
							eo.eSet(attribute, ((Number)event.newValue).intValue());
						} else {
							eo.eSet(attribute, event.newValue);
						}
					}
				} finally {
					uiModification.remove(element);
				}
			}
			
		});
		initWidget(element, widget);
		
		return widget;
	}
	
	public final IEclipseContext setupRenderingContext(M element) {
		IEclipseContext context = (IEclipseContext) element.getTransientData().get(RENDERING_CONTEXT_KEY);
		if( context == null ) {
			context = _context.createChild("Element RenderingContext");
			element.getTransientData().put(RENDERING_CONTEXT_KEY, context);
			context.set(CONTEXT_DOM_ELEMENT, element);
			initRenderingContext(element, context);
			
			try {
				contextModification.put(element,Boolean.TRUE);
				EObject eo;
				if( element instanceof MPlaceholder ) {
					eo = (EObject) ((MPlaceholder)element).getRef();
				} else {
					eo = (EObject) element;
				}
				
				for( EAttribute e : eo.eClass().getEAllAttributes() ) {
					context.set(e.getName(), eo.eGet(e));
				}
				
				// Localized Label/Tooltip treatment
				if( eo instanceof MUILabel ) {
					MUILabel l = (MUILabel) eo;
					context.set(ATTRIBUTE_localizedLabel, l.getLocalizedLabel());
					context.set(ATTRIBUTE_localizedTooltip, l.getLocalizedTooltip());
				}
			} finally {
				contentProcessing.remove(element);
			}
		}
		return context;
	}
	
	protected void registerEventListener(IEventBroker broker, String topic) {
		broker.subscribe(topic, new EventHandler() {
			
			@Override
			public void handleEvent(Event event) {
				Object changedObj = event.getProperty(UIEvents.EventTags.ELEMENT);
				if( ! (changedObj instanceof MUIElement) ) {
					return;
				}
				
				MUIElement e = (MUIElement) changedObj;
				// There is already a modification in process
				if( inContextModification(e) ) {
					return;
				}
				
				try {
					contextModification.put(e,Boolean.TRUE);
					
					if( changedObj instanceof MUIElement ) {
						
						if( e.getRenderer() == BaseRenderer.this ) {
							IEclipseContext ctx = (IEclipseContext) e.getTransientData().get(RENDERING_CONTEXT_KEY);
							if( ctx != null ) {
								ctx.set(event.getProperty(UIEvents.EventTags.ATTNAME).toString(), event.getProperty(UIEvents.EventTags.NEW_VALUE));
								if( e instanceof MUILabel ) {
									MUILabel l = (MUILabel) e;
									if( event.getProperty(UIEvents.EventTags.ATTNAME).equals(UIEvents.UILabel.LABEL) ) {
										ctx.set(ATTRIBUTE_localizedLabel, l.getLocalizedLabel());	
									} else if( event.getProperty(UIEvents.EventTags.ATTNAME).equals(UIEvents.UILabel.TOOLTIP) ) {
										ctx.set(ATTRIBUTE_localizedTooltip, l.getLocalizedTooltip());	
									}									
								}
							}
						}
					}
				} finally {
					contextModification.remove(e);
				}
			}
		});
	}
	
	protected void initRenderingContext(M element, IEclipseContext context) {
		
	}
	
	protected void initWidget(M element, W widget) {
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void destroyWidget(M element) {
		if( element.getTransientData().containsKey(RENDERING_CONTEXT_KEY) ) {
			unbindWidget(element, (W) element.getWidget());
			
			IEclipseContext ctx = (IEclipseContext) element.getTransientData().get(RENDERING_CONTEXT_KEY);
			ctx.dispose();
			element.getTransientData().remove(RENDERING_CONTEXT_KEY);
			
		}
	}
	
	private void unbindWidget(M me, W widget) {
		widget.setDomElement(null);
		me.setWidget(null);
	}
	
	@Override
	public void bindWidget(M me, W widget) {
		widget.setDomElement(me);
		widget.addStyleClasses(me.getTags());
		
		EObject eo = (EObject) me;
		widget.addStyleClasses("M" + eo.eClass().getName());
		
		for( EClass e : eo.eClass().getEAllSuperTypes() ) {
			widget.addStyleClasses("M" + e.getName());
		}
		
		if( me.getElementId() != null ) {
			widget.setStyleId(me.getElementId());
		}
		me.setWidget(widget);
	}

	@Override
	public void postProcess(M element) {
	}
	
	protected IPresentationEngine getPresentationEngine() {
		return _context.get(IPresentationEngine.class);
	}
	
	protected abstract Class<? extends W> getWidgetClass();
	
	@SuppressWarnings("unchecked")
	protected <LW extends WWidget<PM>, PM extends MUIElement> LW engineCreateWidget(PM pm) {
		return (LW) getPresentationEngine().createGui(pm);
	}
	
	@SuppressWarnings("unchecked")
	protected <LW extends WWidget<PM>, PM extends MUIElement> LW engineCreateWidget(PM pm, IEclipseContext context) {
		return (LW) getPresentationEngine().createGui(pm,null,context);
	}
	
	protected IEclipseContext getRenderingContext(M element) {
		return (IEclipseContext) element.getTransientData().get(RENDERING_CONTEXT_KEY);
	}
	
	protected IEclipseContext getContextForParent(MUIElement element) {
		return modelService.getContainingContext(element);
	}

	public IEclipseContext getModelContext(MUIElement part) { 
		if (part instanceof MContext) {
			return ((MContext) part).getContext();
		}
		return getContextForParent(part);
	}

	protected void activate(MPart element, boolean requiresFocus) {
		IEclipseContext curContext = getModelContext(element);
		if (curContext != null) {
			EPartService ps = (EPartService) curContext.get(EPartService.class
					.getName());
			if (ps != null)
				ps.activate(element, requiresFocus);
		}
	}
	
	@Override
	public final void processContent(M element) {
		try {
			contentProcessing.put(element, Boolean.TRUE);
			doProcessContent(element);
		} finally {
			contentProcessing.remove(element);
		}
	}
	
	protected int getRenderedIndex(MUIElement parent, MUIElement element) {
		EObject eElement = (EObject) element;
		
		EObject container = eElement.eContainer();
		@SuppressWarnings("unchecked")
		List<MUIElement> list = (List<MUIElement>) container.eGet(eElement.eContainmentFeature());
		int idx = 0;
		for( MUIElement u : list ) {
			if( u.isToBeRendered() && u.isVisible() ) {
				if( u == element ) {
					return idx;
				}
				idx++;
			}
		}
		return -1;
	}
	
	protected abstract void doProcessContent(M element);
}
