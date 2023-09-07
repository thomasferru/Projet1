package annuaireIsika.Annuaire.Front.BorderPane;

import javafx.scene.layout.BorderPane;

public class MainBorderPane extends BorderPane {
	
	private boolean connect = false;

	public MainBorderPane(boolean connect) {
		super();
		this.connect = connect;

		LeftPart leftPart = new LeftPart();
		TestRight rightPart = new TestRight(this, this.connect);
		CenterPart centerPart = new CenterPart(this, this.connect);

		this.setLeft(leftPart);
		this.setRight(rightPart);
		this.setCenter(centerPart);

	}

}
