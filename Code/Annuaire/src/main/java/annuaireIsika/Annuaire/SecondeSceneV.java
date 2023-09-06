
package annuaireIsika.Annuaire;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SecondeSceneV extends VBox {
	private Scene scene;

	public SecondeSceneV(Scene mainScene) {
		super(24);
		this.scene = scene; 

		Label label = new Label("Entrez votre mot de passe");
		PasswordField password = new PasswordField();
		Button btnValider = new Button("Valider");
		Button btnBack = new Button("Retour");

		this.getChildren().addAll(label, password, btnValider, btnBack);

//		MainBorderPane mainBorderPane = new MainBorderPane();
//		Scene scene = new Scene(mainBorderPane);

		btnValider.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Stage stage = (Stage) SecondeSceneV.this.getScene().getWindow();
				stage.setScene(scene); // Revenez à la scène principale
			}
		});

		btnBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				Stage stage = (Stage) SecondeSceneV.this.getScene().getWindow();
				stage.setScene(scene);
			}
		});

	}
}
