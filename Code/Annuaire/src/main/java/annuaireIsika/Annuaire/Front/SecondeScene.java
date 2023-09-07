
package annuaireIsika.Annuaire.Front;

import annuaireIsika.Annuaire.Front.BorderPane.MainBorderPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SecondeScene extends VBox {
	private String password = "Groupe3";

	public SecondeScene() {
		super(24);

		Label h1 = new Label("Connexion");
		Font fontH1 = Font.loadFont(getClass().getResource("/font/OpenSans-Bold.ttf").toExternalForm(), 34);
		h1.setFont(fontH1);

		PasswordField passwordField = new PasswordField();
		passwordField.setPromptText("Mot de passe");
		passwordField.setMinWidth(316);
		passwordField.setMaxWidth(316);
		
		

		HBox btns = new HBox(16);

		Button backBtn = new Button("Annuler");
		Font fontBtn = Font.loadFont(getClass().getResource("/font/OpenSans-Bold.ttf").toExternalForm(), 14);
		backBtn.setFont(fontBtn);
		backBtn.setStyle("-fx-background-color: #272A33;");
		backBtn.setTextFill(Color.WHITE);
		backBtn.setMinWidth(150);

		Button connectBtn = new Button("Se connecter");
		connectBtn.setFont(fontBtn);
		connectBtn.setStyle("-fx-background-color: #F8C822;");
		connectBtn.setMinWidth(150);

		btns.getChildren().addAll(backBtn, connectBtn);
		btns.setAlignment(Pos.CENTER);

		this.getChildren().addAll(h1, passwordField, btns);
		this.setPadding(new Insets(48));
		this.setAlignment(Pos.CENTER);

		connectBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				if (passwordField.getText().equals(password)) {
					Stage stage = (Stage) SecondeScene.this.getScene().getWindow();
					stage.setScene(new Scene(new MainBorderPane(true), 1024, 640));
					stage.show();
				}else {
					passwordField.setStyle("-fx-border-color: red;");
				}

			}
		});
		
		
		backBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				Stage stage = (Stage) SecondeScene.this.getScene().getWindow();
				stage.setScene(new Scene(new MainBorderPane(false), 1024, 640));
				stage.show();
			}

		});
		
		passwordField.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		        if (passwordField.getText().equals(password)) {
		            Stage stage = (Stage) SecondeScene.this.getScene().getWindow();
		            stage.setScene(new Scene(new MainBorderPane(true), 1024, 640));
		            stage.show();
		        }else {
					passwordField.setStyle("-fx-border-color: red;");
				}
		    }
		});
		
		



	}
}
