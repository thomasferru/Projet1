package annuaireIsika.Annuaire;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TopPart extends HBox{
	
	public TopPart(){
		super();
		Label lb = new Label("Annuaire ISIKA ");
		Button adminBtn = new Button("Admin");
		
		this.getChildren().addAll(lb, adminBtn);
		this.setStyle("-fx-background-color: RED;");
		
		
		adminBtn.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		
		public void handle(ActionEvent event) { 
			
			SecondScene secondScene = new SecondScene();
			Scene scene = new Scene(secondScene);
			Stage stage = (Stage) TopPart.this.getScene().getWindow();
			stage.setScene(scene);
			
			
			
		}
		});
		
	
	}}