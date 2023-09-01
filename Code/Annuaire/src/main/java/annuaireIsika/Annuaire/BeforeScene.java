package annuaireIsika.Annuaire;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BeforeScene extends VBox{
	
	public BeforeScene() {
		
		super();
		
		Label labelAccueil = new Label("Bienvenue dans l'Annuaire d'Isika");
		Button btnGo = new Button("C'est parti!");
		
		this.getChildren().addAll(labelAccueil, btnGo);
		this.setStyle("-fx-background-color: blue;");
		
		btnGo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			
			public void handle(ActionEvent event) { 
				
				MainBorderPane mainBorderPane = new MainBorderPane();
				Scene scene = new Scene(mainBorderPane);
				Stage stage = (Stage) BeforeScene.this.getScene().getWindow();
				stage.setScene(scene);
				
				
			
			}
			});
		
	}

}
