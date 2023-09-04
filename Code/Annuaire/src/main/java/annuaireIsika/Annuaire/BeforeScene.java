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
import javafx.stage.Stage;

public class BeforeScene extends VBox {

	public BeforeScene() {

		super();

		Label labelAccueil = new Label("Bienvenue dans l'Annuaire d'Isika");
		labelAccueil.setPrefSize(800, 300);
		labelAccueil.setAlignment(Pos.CENTER);
		
		Button admin = new Button("Se connecter en tant qu'administrateur");
		admin.setPrefSize(800, 70);
		admin.setAlignment(Pos.TOP_CENTER);
		
		Button user = new Button("Se connecter en tant qu'utilisateur");
		user.setPrefSize(800,70);
		user.setAlignment(Pos.TOP_CENTER);

		this.setStyle("-fx-background-color: beige;");
		
		//Mettre un logo
        
		File logo = new File("./resources/Images/Logo_Isika.jpg");
		Image img = new Image(logo.toURI().toString());		
		
		ImageView logoView = new ImageView(img);
		//logoView.setFitWidth(70); // Largeur souhaitée
		//logoView.setFitHeight(70);// Hauteur souhaitée
		
		//Mettre le logo en haut à gauche
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.TOP_LEFT);
		hbox.getChildren().add(logoView);
		//Scene scene = new Scene(hbox, 800, 600);
		
		this.getChildren().addAll(labelAccueil, admin, user, logoView);

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
