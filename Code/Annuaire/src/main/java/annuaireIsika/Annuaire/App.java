package annuaireIsika.Annuaire;

import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
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
        stage.show();
//        System.out.println(Stagiaire.loadFromTheFile());
//        System.out.println(fromArrayToTree(Stagiaire.loadFromTheFile()).getRoot().affich());
        System.out.println(fromArrayToTree(Stagiaire.loadFromTheFile()).getRoot().makeAList());
        
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