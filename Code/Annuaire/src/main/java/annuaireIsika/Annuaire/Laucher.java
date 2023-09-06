package annuaireIsika.Annuaire;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class Laucher {

	public static void main(String[] args) {
		Stagiaire stagFile = new Stagiaire(null, null, null, null, 0);
		
		BinaryTree arbre = fromArrayToTree(stagFile.loadFromTheFile());
		
		System.out.println(stagFile.loadFromTheFile());
		System.out.println(arbre);
		arbre.treeToFile();
		arbre.read();
}

	private static BinaryTree fromArrayToTree(List<Stagiaire> stagiaires) {
		// TODO Auto-generated method stub
		BinaryTree result = new BinaryTree((stagiaires.get(0)));
		for (Stagiaire stagiaire : stagiaires) {
			result.getRoot().ajouter(stagiaire);
		}
		return result;
	}}
