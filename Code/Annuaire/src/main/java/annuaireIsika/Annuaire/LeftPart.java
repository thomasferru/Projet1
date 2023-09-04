package annuaireIsika.Annuaire;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class LeftPart extends VBox {


	public LeftPart() {
		super(60);

		// Création des champs pour la recherche simple
		
		Label lb = new Label("Recherche ");
		
		TextField txtFieldNom = new TextField();
		txtFieldNom.setPromptText("Nom");
		
		TextField txtFieldPrenom = new TextField();
		txtFieldPrenom.setPromptText("Prenom");
		
		
		Button btnValider = new Button("Valider ");	
		this.getChildren().addAll(lb,txtFieldNom,txtFieldPrenom,btnValider);
		this.setStyle("-fx-background-color: LIGHTGREEN;");
		
	}
	}
		

		
