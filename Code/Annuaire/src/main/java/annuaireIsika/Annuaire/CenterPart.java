package annuaireIsika.Annuaire;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class CenterPart extends VBox {
	public CenterPart() {
		super();

		Label test = new Label("test");

		ScrollPane centerScrollPane = new ScrollPane();
		centerScrollPane.setHmin(300);
		centerScrollPane.setHmax(800);
//		TestTv tableView = new TestTv();
		
//		centerScrollPane.setContent(tableView);

		this.getChildren().addAll(test, centerScrollPane);

		

	

	}

}
