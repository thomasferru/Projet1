package annuaireIsika.Annuaire.Front.BorderPane;

import java.io.File;
import java.io.IOException;
import java.util.List;

import annuaireIsika.Annuaire.Front.AddView;
import annuaireIsika.Annuaire.Front.GeneratePDF;
import annuaireIsika.Annuaire.Front.TableV;
import annuaireIsika.Annuaire.back.BinToList;
import annuaireIsika.Annuaire.back.Stagiaire;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CenterPart extends VBox {
	private boolean connect;
	private MainBorderPane mainBorderPane;
	private List<Stagiaire> list;

	public CenterPart(MainBorderPane mainBorderPane,boolean connect,List<Stagiaire> list ) throws IOException {
		super();
		this.connect = connect;
		this.list = list;


		Label h1 = new Label("STAGIAIRES ISIKA");
		Font fontH1 = Font.loadFont(getClass().getResource("/font/OpenSans-Bold.ttf").toExternalForm(), 34);
		h1.setFont(fontH1);

		VBox tbvContainer = new VBox();
		TableV tbView = new TableV(this.connect, list);
//		TableV tbView = new TableV();
		tbvContainer.getChildren().addAll(tbView);

		// btns contener
		HBox btnsContainer = new HBox(24);

		Button btnPdf = new Button("Générer PDF");
		Font fontBtn = Font.loadFont(getClass().getResource("/font/OpenSans-Bold.ttf").toExternalForm(), 14);

		btnPdf.setFont(fontBtn);
		btnPdf.setStyle("-fx-background-color: #F8C822;");
		btnPdf.setPrefWidth(150);
		btnPdf.setOnMousePressed(event -> {

			btnPdf.setOpacity(0.5);
		});

		btnPdf.setOnMouseReleased(event -> {

			btnPdf.setOpacity(1.0);
		});

		btnPdf.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setInitialFileName("tbv.pdf"); // Nom de fichier par défaut
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

			Stage primaryStage = (Stage) getScene().getWindow();
			File outputFile = fileChooser.showSaveDialog(primaryStage);

			if (outputFile != null) {
				String outputPath = outputFile.getAbsolutePath();
				GeneratePDF.exportToPdf(tbView, outputPath);
				System.out.println("test");

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("PDF créé");
				alert.setHeaderText(null);
				alert.setContentText("Le fichier PDF a été créé avec succès.");
				alert.showAndWait();
			}
		});


		Button addBtn = new Button("Ajouter");
		addBtn.setFont(fontBtn);
		addBtn.setStyle("-fx-background-color: #272A33;");
		addBtn.setTextFill(Color.WHITE);
		addBtn.setPrefWidth(150);
		addBtn.setOnMousePressed(event -> {

			addBtn.setOpacity(0.5);
		});

		addBtn.setOnMouseReleased(event -> {

			addBtn.setOpacity(1.0);
		});

		addBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				mainBorderPane.setCenter(new AddView(mainBorderPane));
//				adminLbl.setTextFill(Color.RED);
			}
		});

		btnsContainer.getChildren().addAll(addBtn, btnPdf);
		btnsContainer.setAlignment(Pos.CENTER);

		// container
		VBox container = new VBox(24);
		if (connect == true) {
			container.getChildren().addAll(h1, tbvContainer, btnsContainer);
		}else {
			container.getChildren().addAll(h1, tbvContainer);
		}

		container.setAlignment(Pos.CENTER);

		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(container);
		this.setPadding(new Insets(48));

	}

}
