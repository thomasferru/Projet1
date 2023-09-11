package annuaireIsika.Annuaire.back;

import java.io.IOException;
import java.util.List;

public class Laucher {

	public static void main(String[] args) throws IOException, InterruptedException {
		Stagiaire stagFile = new Stagiaire(null, null, null, null, 0);
		BinaryTree tree = new BinaryTree(stagFile);
		tree.treeToFile();
		
		;
	}
}
