package at.bestsolution.efxclipse.tooling.pde.ui.wizard.rcp.tpl

import at.bestsolution.efxclipse.tooling.rrobot.model.task.Generator
import at.bestsolution.efxclipse.tooling.rrobot.model.task.DynamicFile
import java.util.Map
import static extension at.bestsolution.efxclipse.tooling.pde.ui.wizard.Util.*

class ViewPartTpl implements Generator<DynamicFile> {
	
	override generate(DynamicFile file, Map<String,Object> data) {
		val bundleId = file.getVariableValue("bundleId");
		val packageName = file.getCuPackagename;
		return generate(bundleId,packageName).toStream 
	}
	
	def generate(String bundleId, String packageName) '''package «packageName»;

import at.bestsolution.efxclipse.runtime.workbench3.FXViewPart;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;


public class MainViewPart extends FXViewPart {
	public static final String ID = "«bundleId».MainViewPart";
	
	private Button button;
	
	@Override
	protected Scene createFxScene() {
		BorderPane p = new BorderPane();
		button = new Button("Hello JavaFX/RCP");
		p.setCenter(button);
		Scene s = new Scene(p);
		return s;
	}

	@Override
	protected void setFxFocus() {
		button.requestFocus();
	}
}'''
}