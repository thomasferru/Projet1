package annuaireIsika.Annuaire;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class Laucher {

	public static void main(String[] args) {
		
		ConvertTreeToBinaryFile test = new ConvertTreeToBinaryFile((fromArrayToTree(Stagiaire.loadFromTheFile())));
		test.treeToListTrie();
		
		Stagiaire.loadFromTheFile();
       
		// TODO Auto-generated method stub
		try (FileOutputStream fos = new FileOutputStream("example.bin");
			     ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			
		    oos.writeObject(test.treeToListTrie());
			 fromArrayToTree(Stagiaire.loadFromTheFile());
			} catch (IOException e) {
			    e.printStackTrace();
			}

		
	}
	
	  public static BinaryTree fromArrayToTree(List<Stagiaire> stagiaires) {
			BinaryTree result = new BinaryTree((stagiaires.get(0)));
			for (Stagiaire stagiaire : stagiaires) {
				result.getRoot().ajouter(stagiaire);
			}
			return result;
			
		}

}
