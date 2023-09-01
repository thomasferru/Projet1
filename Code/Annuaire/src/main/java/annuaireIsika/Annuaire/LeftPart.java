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

		Label lb = new Label("Recherche ");
		TextField txtFieldNom = new TextField("Nom ");
		TextFlow txtFlowNom = new TextFlow(new Text("Nom"));

		TextField txtFieldPrenom = new TextField("Prenom ");
		TextFlow txtFlowPrenom = new TextFlow(new Text("Prenom"));
		Button btnValider = new Button("Valider ");
		this.getChildren().addAll(lb, txtFieldNom, txtFieldPrenom, btnValider);
		this.setStyle("-fx-background-color: LIGHTGREEN;");

		txtFieldNom.setOnMouseClicked(event -> {
			if (txtFieldNom.getText().isEmpty()) {
				txtFlowNom.setVisible(false);
			}
		});

		txtFieldNom.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue && txtFieldNom.getText().isEmpty()) {
				txtFlowNom.setVisible(true);
			}
		});

		txtFieldPrenom.setOnMouseClicked(event -> {
			if (txtFieldPrenom.getText().isEmpty()) {
				txtFlowPrenom.setVisible(false);
			}
		});

		txtFieldPrenom.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue && txtFieldPrenom.getText().isEmpty()) {
				txtFlowPrenom.setVisible(true);
			}
		});

	}

}
