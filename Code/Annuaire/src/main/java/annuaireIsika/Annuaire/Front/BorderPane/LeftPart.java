package annuaireIsika.Annuaire.Front.BorderPane;

import java.io.IOException;
import java.util.List;

import annuaireIsika.Annuaire.back.BinToList;
import annuaireIsika.Annuaire.back.Stagiaire;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LeftPart extends VBox {

	public LeftPart(MainBorderPane mainBorderPane) {
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

		Button btnValider = new Button("Valider ");
		btnValider.setFont(fontlbl);
		btnValider.setStyle("-fx-background-color: #F8C822;");
		btnValider.setPrefWidth(150);
		btnValider.setOnMousePressed(event -> {

			btnValider.setOpacity(0.5);
		});

		btnValider.setOnMouseReleased(event -> {

			btnValider.setOpacity(1.0);
		});
		btnValider.setOnAction(event ->{
			BinToList test;
			try {
				test = new BinToList();
				List<Stagiaire> resultatRecherche = test.Rechercher(txtFieldNom.getText());
				System.out.println("test"+ resultatRecherche);
				mainBorderPane.setCenter(new CenterPart(mainBorderPane, mainBorderPane.isConnect(), resultatRecherche));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});


		Button btnRetour = new Button("Retour");
		btnRetour.setFont(fontlbl);
		btnRetour.setStyle("-fx-background-color: #ffffff;");
		btnRetour.setTextFill(Color.web("#272A33"));
		btnRetour.setPrefWidth(150);
		btnRetour.setOnMousePressed(event -> {

			btnRetour.setOpacity(0.5);
		});

		btnRetour.setOnMouseReleased(event -> {

			btnRetour.setOpacity(1.0);
		});
		btnRetour.setOnAction(event ->{
			BinToList test;
			try {
				test = new BinToList();
				List<Stagiaire> resultatRecherche = test.binToList();
				System.out.println("test"+ resultatRecherche);
				mainBorderPane.setCenter(new CenterPart(mainBorderPane, mainBorderPane.isConnect(), resultatRecherche));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

		searchVB.getChildren().addAll(lb, txtFieldNom, btnValider, btnRetour);
		this.getChildren().addAll(searchVB);
		this.setStyle("-fx-background-color: #272A33;");
		this.setPrefWidth(224);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(24));

	}
}
