package annuaireIsika.Annuaire;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class Laucher {

	public static void main(String[] args) {
		
		ConvertTreeToBinaryFile arbreEnBinaire = new ConvertTreeToBinaryFile((fromArrayToTree(Stagiaire.loadFromTheFile())));
		arbreEnBinaire.treeToFile();
		
       
		

}

	private static BinaryTree fromArrayToTree(List<Stagiaire> stagiaires) {
		// TODO Auto-generated method stub
		BinaryTree result = new BinaryTree((stagiaires.get(0)));
		for (Stagiaire stagiaire : stagiaires) {
			result.getRoot().ajouter(stagiaire);
		}
		return result;
	}}
