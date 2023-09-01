package annuaireIsika.Annuaire;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class CenterPart extends VBox {
	public CenterPart() {
		super(); 
		
		Label test = new Label("test"); 
		
				
		ScrollPane  centerScrollPane = new ScrollPane();
		centerScrollPane.setHmin(300);
		centerScrollPane.setHmax(800);
		VBox insideScrollPane = new VBox(); 
		
		
		
		

		
		centerScrollPane.setContent(insideScrollPane);
		
		this.getChildren().addAll(test, centerScrollPane);

		
		
		
	}

}



 