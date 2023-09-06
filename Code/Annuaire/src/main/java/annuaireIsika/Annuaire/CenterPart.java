package annuaireIsika.Annuaire;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class CenterPart extends VBox {
	public CenterPart(Scene mainScene) {
		super();

		Label h1 = new Label("STAGIAIRES ISIKA");
		Font fontH1 = Font.loadFont(getClass().getResource("/font/OpenSans-Bold.ttf").toExternalForm(), 34);
		h1.setFont(fontH1);

		VBox tbvContainer = new VBox();
		TestTv tbView = new TestTv();
		tbvContainer.getChildren().addAll(tbView);
		
		Button btnPdf = new Button("Générer PDF");
		Font fontBtnPDF = Font.loadFont(getClass().getResource("/font/OpenSans-Bold.ttf").toExternalForm(), 14);

		btnPdf.setFont(fontBtnPDF);
		btnPdf.setStyle("-fx-background-color: #F8C822;");
		btnPdf.setPrefWidth(150);
		
		//container 
		VBox container = new VBox(24); 
		container.getChildren().addAll(h1, tbvContainer, btnPdf);
		container.setAlignment(Pos.CENTER);

		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(container);
		this.setPadding(new Insets(48));

	}

}
