package annuaireIsika.Annuaire;

public class BinaryTree {

	private Node root;

	
	
	public BinaryTree(Stagiaire root) {
		super();
		this.root = new Node(root);
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}
	
	
	
}
