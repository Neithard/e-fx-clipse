package at.bestsolution.efxclipse.runtime.examples.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class ComboExample extends SWTApplication {

	@Override
	public void run() {
		Display d = new Display();
		Shell s = new Shell(d);
		s.setBounds(50, 50, 300, 300);
		s.setLayout(new GridLayout(2,false));
		s.setText("Combo Example");
		
		Label l = new Label(s, SWT.NONE);
		l.setText("Country");
		
		Combo combo = new Combo(s, SWT.READ_ONLY);
		combo.setItems(new String[] {"Austria","Canada","Germany","USA"});
		combo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		s.open();
		
		spinEventLoop();
	}
}
