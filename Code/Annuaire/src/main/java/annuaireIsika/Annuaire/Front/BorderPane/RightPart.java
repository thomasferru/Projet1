package annuaireIsika.Annuaire.Front.BorderPane;

import java.io.File;
import java.io.IOException;

import annuaireIsika.Annuaire.Front.SecondeScene;
import annuaireIsika.Annuaire.back.BinToList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RightPart extends BorderPane {
	


	public RightPart() {
		super();
		
		

// ---début admin

		HBox admin = new HBox(8);

		File padlockIcon = new File("src/main/resources/Images/Padlock.png");
		
		Image img = new Image(padlockIcon.toURI().toString());

		ImageView padlockView = new ImageView(img);

		Label adminLbl = new Label("admin");
		adminLbl.setTextFill(Color.WHITE);

		admin.getChildren().addAll(padlockView, adminLbl);

		Button adminBtn = new Button();
		adminBtn.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
		adminBtn.setGraphic(admin);
		adminBtn.setAlignment(Pos.TOP_RIGHT);

		// test action
		adminBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Scene connect = new Scene(new SecondeScene(), 1024, 640);
				Stage primaryStage = (Stage) adminBtn.getScene().getWindow();
				primaryStage.setScene(connect);

			}
		});
// ---fin

// ---début searchbox
		VBox searchVB = new VBox(24);
		searchVB.setAlignment(Pos.CENTER);

		Label lb = new Label("Recherche avancée");
		Font fontlbl = Font.loadFont(getClass().getResource("/font/OpenSans-Bold.ttf").toExternalForm(), 14);
		lb.setFont(fontlbl);
		lb.setTextFill(Color.WHITE);

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

		Button btnValider = new Button("Valider ");
		btnValider.setFont(fontlbl);
		btnValider.setStyle("-fx-background-color: #F8C822;");
		
		btnValider.setPrefWidth(150);

		searchVB.getChildren().addAll(lb, txtFieldNom, txtFieldPrenom, txtFieldDepartement, txtFieldClasse,
				txtFieldAnneEntreeFormation, btnValider);
//fin
		this.setTop(adminBtn);
		this.setCenter(searchVB);

		this.setStyle("-fx-background-color: #272A33;");
		this.setPrefWidth(224);
		this.setPadding(new Insets(24));
		
	}}

