package annuaireIsika.Annuaire;

import javafx.scene.layout.BorderPane;

public class MainBorderPane extends BorderPane {
	
	public MainBorderPane() {
		super();
		LeftPart leftPart = new LeftPart();
		RightPart rightPart = new RightPart();
		TopPart topPart = new TopPart();
		BottomPart botPart = new BottomPart();
		CenterPart centerPart = new CenterPart();
		
		this.setLeft(leftPart);
		this.setRight(rightPart);
		this.setTop(topPart);
		this.setBottom(botPart);
		this.setCenter(centerPart);
	}
	

}
