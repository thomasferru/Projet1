package annuaireIsika.Annuaire;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class LeftPart extends VBox{
	
	public LeftPart() {
		super(60);
		
		Label lb = new Label("Recherche ");
		TextField txtFieldNom = new TextField("Nom ");
		TextField txtFieldPrenom = new TextField("Prenom ");
		Button btnValider = new Button("Valider ");	
		this.getChildren().addAll(lb,txtFieldNom,txtFieldPrenom,btnValider);
		this.setStyle("-fx-background-color: LIGHTGREEN;");
	}

}
