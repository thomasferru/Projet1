package annuaireIsika.Annuaire;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class RightPart extends VBox {
	
	public RightPart() {
		super(24);
		
		Label lb = new Label("Recherche avancée ");
		TextField txtFieldNom = new TextField("Nom ");
		TextField txtFieldPrenom = new TextField("Prenom ");
		TextField txtFieldDep = new TextField("Departement ");
		TextField txtFieldClasse = new TextField("Classe ");
		TextField txtFieldEntrerForm = new TextField("Entrer en formation ");
		Button btnValider = new Button("Valider ");	
		this.getChildren().addAll(lb,txtFieldNom,txtFieldPrenom,txtFieldDep,txtFieldClasse,txtFieldEntrerForm,btnValider);
		this.setStyle("-fx-background-color: LIGHTBLUE;");
	}

}
