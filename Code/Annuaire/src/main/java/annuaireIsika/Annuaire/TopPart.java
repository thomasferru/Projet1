package annuaireIsika.Annuaire;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class TopPart extends HBox{
	
	public TopPart(){
		super();
		Label lb = new Label("Annuaire ISIKA ");
		this.getChildren().addAll(lb);
		this.setStyle("-fx-background-color: RED;");
	}
	
}
