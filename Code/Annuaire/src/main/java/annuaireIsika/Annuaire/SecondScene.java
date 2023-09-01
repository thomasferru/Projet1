package annuaireIsika.Annuaire;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SecondScene extends VBox {

	public SecondScene() {
		
		super(24);

		PasswordField password = new PasswordField();
		Button btnValider = new Button("Valider");
		Button btnBack = new Button("Retour");

		this.getChildren().addAll(password, btnValider, btnBack);
		
		MainBorderPane mainBorderPane = new MainBorderPane();
			Scene scene = new Scene(mainBorderPane);
			
			btnValider.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) { 
				
			Stage stage = (Stage) SecondScene.this.getScene().getWindow();
				stage.setScene(scene);
				
			}
			});
		
		btnBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override

			public void handle(ActionEvent event) {

				Stage stage = (Stage) SecondScene.this.getScene().getWindow();
				stage.setScene(scene);
		}

	

	});

}
}
