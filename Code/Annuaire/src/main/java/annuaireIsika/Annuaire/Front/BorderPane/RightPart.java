package annuaireIsika.Annuaire.Front.BorderPane;

import java.io.File;
import java.io.IOException;
import java.util.List;

import annuaireIsika.Annuaire.Front.SecondeScene;
import annuaireIsika.Annuaire.back.BinToList;
import annuaireIsika.Annuaire.back.Stagiaire;
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

	private MainBorderPane mainBorderPane;
//	private Label adminLbl;
//	private Button adminBtn;

	public RightPart(MainBorderPane mainBorderPane, boolean connect) {
		super();
		this.mainBorderPane = mainBorderPane;

		if (connect == true) {
			HBox admin = new HBox(8);

			File padlockIcon = new File("src/main/resources/Images/Padlock.png");
			Image img = new Image(padlockIcon.toURI().toString());
			ImageView padlockView = new ImageView(img);

			Button adminBtn = new Button();
			adminBtn.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
			adminBtn.setGraphic(admin);
			adminBtn.setAlignment(Pos.TOP_RIGHT);

			Label adminLbl = new Label("Se déconnecter");
			adminLbl.setTextFill(Color.WHITE);

			admin.getChildren().addAll(padlockView, adminLbl);
			admin.setOpacity(1.0);

			admin.setOnMousePressed(event -> {

				admin.setOpacity(0.5);
			});

			admin.setOnMouseReleased(event -> {

				admin.setOpacity(1.0);
			});
			this.setTop(adminBtn);

			adminBtn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
//					mainBorderPane.setCenter(new MainBorderPane(false));
					Stage stage = (Stage) RightPart.this.getScene().getWindow();
					try {
						stage.setScene(new Scene(new MainBorderPane(false), 1024, 640));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					stage.show();
				}
			});

		} else {
			HBox notAdmin = new HBox(8);

			File padlockIcon = new File("src/main/resources/Images/Padlock.png");
			Image img = new Image(padlockIcon.toURI().toString());
			ImageView padlockView = new ImageView(img);

			Button connectBtn = new Button();
			connectBtn.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
			connectBtn.setGraphic(notAdmin);
			connectBtn.setAlignment(Pos.TOP_RIGHT);

			Label adminLbl = new Label("Admin");
			adminLbl.setTextFill(Color.WHITE);

			notAdmin.getChildren().addAll(padlockView, adminLbl, connectBtn);
			notAdmin.setOpacity(1.0);

			notAdmin.setOnMousePressed(event -> {

				notAdmin.setOpacity(0.5);
			});

			notAdmin.setOnMouseReleased(event -> {

				notAdmin.setOpacity(1.0);
			});
			this.setTop(connectBtn);

			// test action
			connectBtn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					mainBorderPane.setCenter(new SecondeScene());
//					adminLbl.setTextFill(Color.RED);
				}
			});
			// ---fin
		}

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
		btnValider.setOnMousePressed(event -> {

			btnValider.setOpacity(0.5);
		});

		btnValider.setOnMouseReleased(event -> {

			btnValider.setOpacity(1.0);
		});
		btnValider.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				BinToList test;
				String nom = txtFieldNom.getText();
				String prenom = txtFieldPrenom.getText();
				String departement = txtFieldDepartement.getText();
				String promo = txtFieldClasse.getText();
				int anneePromo;
				if (txtFieldAnneEntreeFormation.getText().compareTo("") == 0) {
					anneePromo = 0;
				} else {
					anneePromo = Integer.parseInt(txtFieldAnneEntreeFormation.getText());
				}
				try {
					test = new BinToList();
					List<Stagiaire> resultatRecherche = test.rechercheMultiple(txtFieldNom.getText(),
							txtFieldPrenom.getText(), txtFieldDepartement.getText(),txtFieldClasse.getText() ,anneePromo);
					System.out.println("test" + resultatRecherche);
					mainBorderPane
							.setCenter(new CenterPart(mainBorderPane, mainBorderPane.isConnect(), resultatRecherche));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

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

		searchVB.getChildren().addAll(lb, txtFieldNom, txtFieldPrenom, txtFieldDepartement, txtFieldClasse,
				txtFieldAnneEntreeFormation, btnValider, btnRetour);
		// fin
		this.setCenter(searchVB);

		this.setStyle("-fx-background-color: #272A33;");
		this.setPrefWidth(224);
		this.setPadding(new Insets(24));



	}
}
