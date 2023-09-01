package annuaireIsika.Annuaire;

import java.util.List;

public class BinaryTree {

	private Node root;

	
	
	public BinaryTree(Stagiaire root) {
		super();
		this.root = new Node(root);
	}
	
	//transformer la liste stagiaire en arbre binaire
	
	

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}
	
	
	
}
