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


try (BufferedReader reader = new BufferedReader(new FileReader("./resources/STAGIAIRES.DON"))) {
	String line;

	while ((line = reader.readLine()) != null) {
		System.out.println(line);

	}
} catch (IOException e) {
	e.printStackTrace();
	
	
	
}
 mes donnees ressemble à ca : 
 LACROIX
 Pascale
 91
 BOBI 5
 2008
 *
 LAVEAU
 Valentin
 33
 BOBI 7
 2018
 *
 Je voudrai 