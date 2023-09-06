package annuaireIsika.Annuaire;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class MainBorderPane extends BorderPane {
	 private Scene scene;

	public MainBorderPane(Scene mainScene) {
		super();
		this.scene = scene;
		LeftPart leftPart = new LeftPart();
		RightPart rightPart = new RightPart(scene);
		CenterPart centerPart = new CenterPart(scene);

		this.setLeft(leftPart);
		this.setRight(rightPart);

		this.setCenter(centerPart);

	}
	


}
