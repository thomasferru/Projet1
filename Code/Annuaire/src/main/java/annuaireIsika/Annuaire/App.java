package annuaireIsika.Annuaire;

import annuaireIsika.Annuaire.Front.BorderPane.MainBorderPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        MainBorderPane root = new MainBorderPane(false);
        Scene scene = new Scene(root,1024,640);
        stage.setScene(scene);
        stage.setTitle("[-Projet1-] Groupe3");
        stage.setResizable(false);
        stage.show();
        
    }


    
   
    
    public static void main(String[] args) {
        launch();
    }

}