package at.bestsolution.efxclipse.tooling.fxgraph.ui.preview;


import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.model.IXtextModelListener;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import at.bestsolution.efxclipse.tooling.fxgraph.fXGraph.ComponentDefinition;
import at.bestsolution.efxclipse.tooling.fxgraph.fXGraph.Model;
import at.bestsolution.efxclipse.tooling.fxgraph.generator.FXGraphGenerator;
import at.bestsolution.efxclipse.tooling.fxgraph.ui.preview.LivePreviewPart.ContentData;

import com.google.inject.Inject;

public class LivePreviewSynchronizer implements IPartListener, IXtextModelListener {
	@Inject
	private LivePreviewPart view;
	
	private IXtextDocument lastActiveDocument;
	
	public void partActivated(IWorkbenchPart part) {
		updateView(part);
	}

	private void updateView(IWorkbenchPart part) {
		System.err.println("Part changed to: " + part);
		if (part instanceof XtextEditor) {
			XtextEditor xtextEditor = (XtextEditor) part;
			IXtextDocument xtextDocument = xtextEditor.getDocument();
			if (xtextDocument != lastActiveDocument) {
//				selectionLinker.setXtextEditor(xtextEditor);
				final ContentData contents = xtextDocument.readOnly(new IUnitOfWork<ContentData, XtextResource>() {
					public ContentData exec(XtextResource resource) throws Exception {
						return createContents(resource);
					}
				});
				if (contents != null) {
					view.setContents(contents);
					if (lastActiveDocument != null) {
						lastActiveDocument.removeModelListener(this);
					}
					lastActiveDocument = xtextDocument;
					lastActiveDocument.addModelListener(this);
				}
			}
		}
	}
	
	private ContentData createContents(XtextResource resource) {
		EList<EObject> contents = resource.getContents();
		if (!contents.isEmpty()) {
			EObject rootObject = contents.get(0);
			if( rootObject instanceof Model ) {
				FXGraphGenerator generator = new FXGraphGenerator();
				ComponentDefinition def = ((Model) rootObject).getComponentDef();
				List<String> l;
				if( def != null ) {
					l = def.getCssFiles();
				} else {
					l = Collections.emptyList();
				}
				
				return new ContentData(generator.doGeneratePreview(resource), l, def != null ? def.getPreviewResourceBundle() : null);				
			}
		}
		
		return null;
	}

	@Override
	public void modelChanged(XtextResource resource) {
		view.setContents(createContents(resource));
	}

	@Override
	public void partBroughtToTop(IWorkbenchPart part) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void partClosed(IWorkbenchPart part) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void partDeactivated(IWorkbenchPart part) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void partOpened(IWorkbenchPart part) {
		// TODO Auto-generated method stub
		
	}
}
