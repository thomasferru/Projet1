package annuaireIsika.Annuaire;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class CenterPart extends VBox {
	public CenterPart() {
		super();

		Label test = new Label("test");
		VBox tbvContainer = new VBox();
		TestTv tbView = new TestTv();
		tbvContainer.getChildren().addAll(tbView);
		
		

		this.getChildren().addAll(test, tbView);

	}

}
