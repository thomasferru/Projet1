package annuaireIsika.Annuaire.Front.BorderPane;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class MainBorderPane extends BorderPane {
	 private Scene scene;

	public MainBorderPane() {
		super();
		init();
		

	}
	
	public void init() {
		LeftPart leftPart = new LeftPart();
		RightPart rightPart = new RightPart();
		CenterPart centerPart = new CenterPart();

		this.setLeft(leftPart);
		this.setRight(rightPart);

		this.setCenter(centerPart);
	}
	


}
