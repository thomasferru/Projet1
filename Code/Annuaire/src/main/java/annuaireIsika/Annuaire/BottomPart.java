package annuaireIsika.Annuaire;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class BottomPart extends HBox{
	
	public BottomPart() {
		super();
		Button btn = new Button("generer pdf ");
		this.getChildren().addAll(btn);
		this.setStyle("-fx-background-color: ORANGE;");
	}

}
