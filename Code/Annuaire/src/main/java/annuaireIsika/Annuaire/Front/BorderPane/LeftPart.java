package annuaireIsika.Annuaire.Front.BorderPane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LeftPart extends VBox {

	public LeftPart() {
		super(60);

		// Création des champs pour la recherche simple

		// Création d'un contenaire pour pouvoir centrer le bloc

		VBox searchVB = new VBox(24);
		searchVB.setAlignment(Pos.CENTER);
		

		Label lb = new Label("Recherche ");
//	
		Font fontlbl = Font.loadFont(getClass().getResource("/font/OpenSans-Bold.ttf").toExternalForm(), 14);
		lb.setFont(fontlbl);
		lb.setTextFill(Color.WHITE);

		TextField txtFieldNom = new TextField();
		Font fontTxtFiel = Font.loadFont(getClass().getResource("/font/OpenSans-Medium.ttf").toExternalForm(), 14);
		txtFieldNom.setFont(fontTxtFiel);
		txtFieldNom.setPromptText("Nom");
		txtFieldNom.setPrefWidth(150);

		TextField txtFieldPrenom = new TextField();
		txtFieldPrenom.setFont(fontTxtFiel);
		txtFieldPrenom.setPromptText("Prenom");
		txtFieldNom.setPrefWidth(150);

		Button btnValider = new Button("Valider ");
		btnValider.setFont(fontlbl);
		btnValider.setStyle("-fx-background-color: #F8C822;");
		btnValider.setPrefWidth(150);
		
		
		
		searchVB.getChildren().addAll(lb, txtFieldNom, txtFieldPrenom, btnValider);
		this.getChildren().addAll(searchVB);
		this.setStyle("-fx-background-color: #272A33;");
		this.setPrefWidth(224);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(24));

	}
}