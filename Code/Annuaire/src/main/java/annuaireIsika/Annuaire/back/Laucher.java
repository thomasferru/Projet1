package annuaireIsika.Annuaire.back;

import java.io.IOException;
import java.util.List;

public class Laucher {

	public static void main(String[] args) throws IOException, InterruptedException {
		Stagiaire stagFile = new Stagiaire(null, null, null, null, 0);
		BinaryTree tree = new BinaryTree(stagFile);
		tree.treeToFile();
		//BinToList test = new BinToList();
		
		//List<Stagiaire> testt = test.binToList(); 
		//System.out.println(testt);

		// System.out.println(stagFile.loadFromTheFile());
		// System.out.println(stagFile.loadFromTheFile().get(3).getNom());
		;
	}
}
//	private static BinaryTree fromArrayToTree(List<Stagiaire> stagiaires) {
//		// TODO Auto-generated method stub
//		BinaryTree result = new BinaryTree((stagiaires.get(0)));
//		for (Stagiaire stagiaire : stagiaires) {
//			result.getRoot().ajouter(stagiaire);
//		}
//		return result;
//	}}
