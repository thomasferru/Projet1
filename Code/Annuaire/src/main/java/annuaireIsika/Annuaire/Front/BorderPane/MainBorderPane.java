package annuaireIsika.Annuaire.Front.BorderPane;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class MainBorderPane extends BorderPane {
	private Scene scene;
	private boolean connect = false;

	public MainBorderPane(boolean connect) {
		super();
		this.connect = connect;

		if (connect == true) {
			TestRight rightPart = new TestRight(this, this.connect);
			CenterPart centerPart = new CenterPart();

			this.setRight(rightPart);
			this.setCenter(centerPart);

		} else {
			LeftPart leftPart = new LeftPart();
			TestRight rightPart = new TestRight(this, this.connect);
			CenterPart centerPart = new CenterPart();

			this.setLeft(leftPart);
			this.setRight(rightPart);
			this.setCenter(centerPart);
		}

	}

}
