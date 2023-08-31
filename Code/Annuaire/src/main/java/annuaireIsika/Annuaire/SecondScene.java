package annuaireIsika.Annuaire;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;

public class SecondScene extends VBox {

	public SecondScene() {
		
		super(24);

		PasswordField password = new PasswordField();
		Button btnValider = new Button("Valider");
		Button btnBack = new Button("Retour");

		this.getChildren().addAll(password, btnValider, btnBack);

	}

}
