package annuaireIsika.Annuaire;

import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
    	
       
    	BeforeScene root = new BeforeScene();
        Scene scene = new Scene(root,500,500);
        stage.setScene(scene);
        stage.show();
//        System.out.println(Stagiaire.loadFromTheFile());
//        System.out.println(fromArrayToTree(Stagiaire.loadFromTheFile()).getRoot().affich());
       // System.out.println(fromArrayToTree(Stagiaire.loadFromTheFile()).getRoot().makeAList());
        
        //Mettre un logo
        
        //Image logo = new Image("src/main/resources/Logo_Isika.jpg");
        //Image img = new Image(homeIcon.toURI().toString());
		//ImageView logoView = new ImageView(logo);
        // root.getChildren().add(logoView);
        //  logo.setFitWidth(32); // Largeur souhaitée
	  	//logo.setFitHeight(32);
    }


    
    public BinaryTree fromArrayToTree(List<Stagiaire> stagiaires) {
		BinaryTree result = new BinaryTree((stagiaires.get(0)));
		for (Stagiaire stagiaire : stagiaires) {
			result.getRoot().ajouter(stagiaire);
		}
		return result;
		
	}
    
    public static void main(String[] args) {
        launch();
    }

}