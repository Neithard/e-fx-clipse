package at.bestsolution.efxclipse.tooling.fxmlx.ui.wizards;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.ui.IJavaElementSearchConstants;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.SelectionDialog;

import at.bestsolution.efxclipse.tooling.fxmlx.ui.internal.FXMLDslActivator;
import at.bestsolution.efxclipse.tooling.fxmlx.ui.wizards.template.FXMLElement;
import at.bestsolution.efxclipse.tooling.ui.wizards.AbstractJDTElementPage;

public class FXMLWizardPage extends AbstractJDTElementPage<FXMLElement> {
	private IType customSelection;
	
	protected FXMLWizardPage(IPackageFragmentRoot froot, IPackageFragment fragment, IWorkspaceRoot fWorkspaceRoot) {
		super("fxgraph", "FXGraph File", "Create a new FXML File", froot, fragment, fWorkspaceRoot);
	}

	@Override
	protected ImageDescriptor getTitleAreaImage(Display display) {
		return FXMLDslActivator.imageDescriptorFromPlugin(FXMLDslActivator.AT_BESTSOLUTION_EFXCLIPSE_TOOLING_FXMLX_FXMLDSL, "/icons/title_banner.png");
	}
	
	@Override
	protected void createFields(Composite parent, DataBindingContext dbc) {
		{
			Label l = new Label(parent, SWT.NONE);
			l.setText("Root Element");
			
			final ComboViewer viewer = new ComboViewer(parent);
			viewer.setLabelProvider(new LabelProvider() {
				@Override
				public String getText(Object element) {
					IType t = (IType)element;
					return t.getElementName() + " - " + t.getPackageFragment().getElementName();
				}
			});
			viewer.setContentProvider(new ArrayContentProvider());
			List<IType> types = getTypes();
			viewer.setInput(types);
			viewer.getControl().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			
			Button button = new Button(parent, SWT.PUSH);
			button.setText("Browse ...");
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					IType type = findContainerType();
					if( type != null ) {
						customSelection = type;
						viewer.setInput(getTypes());
						viewer.setSelection(new StructuredSelection(type));
					}
				}
			});
			
			FXMLElement element = getClazz();
			element.addPropertyChangeListener(new PropertyChangeListener() {
				
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					if( "fragmentRoot".equals(evt.getPropertyName()) ) {
						viewer.setInput(getTypes());
					}
				}
			});
			dbc.bindValue(ViewerProperties.singleSelection().observe(viewer), BeanProperties.value("rootElement").observe(getClazz()));
			
			if( types.size() > 0 ) {
				viewer.setSelection(new StructuredSelection(types.get(0)));
			}
		}
	}
	
	IType findContainerType() {
		if( getClazz().getFragmentRoot() != null ) {
			IJavaProject project= getClazz().getFragmentRoot().getJavaProject();
			
			try {
				IType superType = project.findType("javafx.scene.Parent");
				
				if( superType != null ) {
					IJavaSearchScope searchScope = SearchEngine.createStrictHierarchyScope(project, superType, true, false, null);		
					
					SelectionDialog dialog = JavaUI.createTypeDialog(getShell(), PlatformUI.getWorkbench().getProgressService(), searchScope, IJavaElementSearchConstants.CONSIDER_CLASSES, false, "");
					dialog.setTitle("Find Preloader");
					if (dialog.open() == Window.OK) {
						IType type = (IType) dialog.getResult()[0];
						return type;
					}	
				}
			} catch (JavaModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	protected void revalidate() {
		if( getClazz().getName() == null || getClazz().getName().trim().length() == 0 ) {
			setPageComplete(false);
			setMessage("Enter a name", IMessageProvider.ERROR);
		} else if(Character.isLowerCase(getClazz().getName().charAt(0))) {
			setPageComplete(true);
			setMessage("An FXML file should start with an uppercase", IMessageProvider.WARNING);
		} else {
			setPageComplete(true);
			setMessage(null);
		}
	}
	
	private List<IType> getTypes() {
		List<IType> list = new ArrayList<IType>();
		
		if( getClazz().getFragmentRoot() != null ) {
			IJavaProject jp = getClazz().getFragmentRoot().getJavaProject();
			
			if( customSelection != null ) {
				try {
					IType t = jp.findType(customSelection.getFullyQualifiedName());
					if( t != null && ! list.contains(t) ) {
						list.add(t);
					}
				} catch (JavaModelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if( getDialogSettings() != null ) {
				if( getDialogSettings().getArray(NewFXMLWizard.KEY_LAST_SELECTIONS) != null ) {
					for( String s : getDialogSettings().getArray(NewFXMLWizard.KEY_LAST_SELECTIONS) ) {
						try {
							IType t = jp.findType(s);
							if( t != null && ! list.contains(t) ) {
								list.add(t);
							}
						} catch (JavaModelException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			
			for(String s : new String[] {"AnchorPane", "BorderPane", "FlowPane", "GridPane", "HBox", "Region", "StackPane", "TilePane", "VBox"}) {
				try {
					IType t = jp.findType("javafx.scene.layout."+s);
					if( t != null && ! list.contains(t) ) {
						list.add(t);
					}
				} catch (JavaModelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}
	
	

	@Override
	protected FXMLElement createInstance() {
		return new FXMLElement();
	}
}