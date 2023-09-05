package annuaireIsika.Annuaire;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BeforeScene extends VBox {

	public BeforeScene() {

		super();
		
		//Mettre un logo
		File logo = new File("./resources/Images/Logo_Isika.jpg");
		Image img = new Image(logo.toURI().toString());	
		
		//Mettre le logo en haut à gauche
		ImageView logoView = new ImageView(img);
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.TOP_LEFT);
		hbox.getChildren().add(logoView);
		logoView.setFitWidth(150); // Largeur souhaitée
		logoView.setFitHeight(100);// Hauteur souhaitée

		Label labelAccueil = new Label("Bienvenue dans l'annuaire d'Isika");
		labelAccueil.setPrefSize(800, 300);
		labelAccueil.setFont(Font.font("Tahoma", 30));
		labelAccueil.setAlignment(Pos.CENTER);
		
		Button admin = new Button("Se connecter en tant qu'administrateur");
		admin.setPrefSize(800, 70);
		admin.setAlignment(Pos.TOP_CENTER);
		
		Button user = new Button("Se connecter en tant qu'utilisateur");
		user.setPrefSize(800,70);
		user.setAlignment(Pos.TOP_CENTER);

		this.setStyle("-fx-background-color: beige;");
		
		
		this.getChildren().addAll(logoView,hbox,labelAccueil, admin, user);

		// Ce qui se passe lorsqu'on clique sur le bouton admin

		admin.setOnAction(new EventHandler<ActionEvent>() {
			@Override

			public void handle(ActionEvent event) {

				SecondScene secondScene = new SecondScene();
				Scene scene = new Scene(secondScene);
				Stage stage = (Stage) BeforeScene.this.getScene().getWindow();
				stage.setScene(scene);

			}
		});
		// Ce qui se passe lorsqu'on clique sur le bouton user

		user.setOnAction(new EventHandler<ActionEvent>() {
			@Override

			public void handle(ActionEvent event) {

				MainBorderPane mainBorderPane = new MainBorderPane();
				Scene scene = new Scene(mainBorderPane);
				Stage stage = (Stage) BeforeScene.this.getScene().getWindow();
				stage.setScene(scene);

			}
		});
	}

}
