package annuaireIsika.Annuaire;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class RightPart extends VBox {
	
	public RightPart() {
		super(24);
		
		// Création des champs pour la recherche avancée
		
		Label lb = new Label("Recherche avancée ");
		TextField txtFieldNom = new TextField();
		txtFieldNom.setPromptText("Nom");
		
		TextField txtFieldPrenom = new TextField();
		txtFieldPrenom.setPromptText("Prenom");

		TextField txtFieldDep = new TextField();
		txtFieldDep.setPromptText("Departement");
		
		TextField txtFieldClasse = new TextField();
		txtFieldClasse.setPromptText("Classe");

		
		TextField txtFieldEntreeForm = new TextField();
		txtFieldEntreeForm.setPromptText("Entrée en formation ");

		Button btnValider = new Button("Valider ");	
		this.getChildren().addAll(lb,txtFieldNom,txtFieldPrenom,txtFieldDep,txtFieldClasse,txtFieldEntreeForm,btnValider);
		this.setStyle("-fx-background-color: LIGHTBLUE;");
		
		
	}

}
