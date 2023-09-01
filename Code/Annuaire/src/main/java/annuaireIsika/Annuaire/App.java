package annuaireIsika.Annuaire;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        MainBorderPane root = new MainBorderPane();
        Scene scene = new Scene(root,500,500);
        stage.setScene(scene);
        ParcoursDon();
//        stage.show();
    }

    public void ParcoursDon() {
    	/*
		 * lit le contenu d'un fichier ligne par ligne, crée un Label pour chaque ligne lue,
		 * puis ajoute chaque Label à une VBox pour afficher les lignes du fichier dans 
		 * l'interface utilisateur JavaFX. Il gère également les exceptions liées à la lecture
		 * du fichier en les imprimant dans la console. 
		  
		 */
		
		
        try (BufferedReader reader = new BufferedReader(new FileReader("./resources/STAGIAIRES.DON"))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
            	System.out.println(line);
            	
            	if (line.contains("*")){
            		 break;
            	}

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    public static void main(String[] args) {
        launch();
    }

}