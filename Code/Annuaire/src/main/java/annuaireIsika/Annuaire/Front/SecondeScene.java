
package annuaireIsika.Annuaire.Front;

import annuaireIsika.Annuaire.Front.BorderPane.MainBorderPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SecondeScene extends VBox {

	public SecondeScene() {
		super(24);
	

		Label label = new Label("Entrez votre mot de passe");
		PasswordField password = new PasswordField();
		Button btnValider = new Button("Valider");
		Button btnBack = new Button("Retour");

		this.getChildren().addAll(label, password, btnValider, btnBack);


		btnValider.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Stage stage = (Stage) SecondeScene.this.getScene().getWindow();
				stage.setScene(new Scene(new MainBorderPane(), 1024, 640));
			}
		});

		btnBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				Stage stage = (Stage) SecondeScene.this.getScene().getWindow();
				stage.setScene(new Scene(new MainBorderPane(), 1024, 640));
				stage.show();
			}
		});

	}
}
