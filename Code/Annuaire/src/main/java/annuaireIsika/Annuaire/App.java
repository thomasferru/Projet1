package annuaireIsika.Annuaire;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        MainBorderPane root = new MainBorderPane(stage.getScene());
        Scene scene = new Scene(root,1024,640);
        stage.setScene(scene);
        stage.show();
        
    }


    
   
    
    public static void main(String[] args) {
        launch();
    }

}