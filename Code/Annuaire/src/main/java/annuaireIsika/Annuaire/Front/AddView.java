package annuaireIsika.Annuaire.Front;

import java.io.File;

import annuaireIsika.Annuaire.Front.BorderPane.MainBorderPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddView extends VBox {
	public AddView() {
		super(24);

		VBox addFields = new VBox(24);
		addFields.setAlignment(Pos.CENTER);

		Label lb = new Label("Ajout Stagiaire");
		Font fontlbl = Font.loadFont(getClass().getResource("/font/OpenSans-Bold.ttf").toExternalForm(), 36);
		lb.setFont(fontlbl);
		lb.setTextFill(Color.web("#272A33"));

		TextField txtFieldNom = new TextField();
		Font fontTxtField = Font.loadFont(getClass().getResource("/font/OpenSans-Medium.ttf").toExternalForm(), 14);
		txtFieldNom.setFont(fontTxtField);
		txtFieldNom.setPromptText("Nom");
		txtFieldNom.setPrefWidth(150);

		TextField txtFieldPrenom = new TextField();
		txtFieldPrenom.setFont(fontTxtField);
		txtFieldPrenom.setPromptText("Prénom");
		txtFieldPrenom.setPrefWidth(150);

		TextField txtFieldDepartement = new TextField();
		txtFieldDepartement.setFont(fontTxtField);
		txtFieldDepartement.setPromptText("Département");
		txtFieldDepartement.setPrefWidth(150);

		TextField txtFieldClasse = new TextField();
		txtFieldClasse.setFont(fontTxtField);
		txtFieldClasse.setPromptText("Classe");
		txtFieldClasse.setPrefWidth(150);

		TextField txtFieldAnneEntreeFormation = new TextField();
		txtFieldAnneEntreeFormation.setFont(fontTxtField);
		txtFieldAnneEntreeFormation.setPromptText("Entrée en formation");
		txtFieldAnneEntreeFormation.setPrefWidth(150);

		HBox btnsContain = new HBox(24);

		Button btnValider = new Button("Ajouter");
		Font fontbtn = Font.loadFont(getClass().getResource("/font/OpenSans-Bold.ttf").toExternalForm(), 14);
		btnValider.setFont(fontbtn);
		btnValider.setStyle("-fx-background-color: F8C822;");
		btnValider.setPrefWidth(150);
		btnValider.setOnAction(event -> {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Satagiaire ajouté");
			alert.setHeaderText(null);
			alert.setContentText("Le stagiaire a été créé avec succès.");
			alert.showAndWait();

		});

		Button btnBack = new Button("Retour");
		btnBack.setFont(fontbtn);
		btnBack.setStyle("-fx-background-color: #272A33;");
		btnBack.setTextFill(Color.WHITE);
		btnBack.setPrefWidth(150);
		btnBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				Stage stage = (Stage) AddView.this.getScene().getWindow();
				stage.setScene(new Scene(new MainBorderPane(true), 1024, 640));
				stage.show();
			}

		});

		btnsContain.getChildren().addAll(btnBack, btnValider);
		btnsContain.setAlignment(Pos.CENTER);

		addFields.getChildren().addAll(lb, txtFieldNom, txtFieldPrenom, txtFieldDepartement, txtFieldClasse,
				txtFieldAnneEntreeFormation, btnsContain);
		this.getChildren().addAll(addFields);
		this.setPadding(new Insets(48));
		this.setAlignment(Pos.CENTER);

	}
}
