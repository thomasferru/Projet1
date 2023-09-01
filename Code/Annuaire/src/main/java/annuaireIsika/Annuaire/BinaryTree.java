package annuaireIsika.Annuaire;

public class BinaryTree {

	Node root;
	
	
	public BinaryTree(Stagiaire root) {
		this.root = new Node(root);
	}

	
	/**
	 * Ajout de node avec add
	 * @param current
	 * @param value
	 * @return
	 */

	private Node addRecursive(Node current, Stagiaire value) {
		if (current == null) {
			return new Node(value);
		}

		if (value.getNom().compareTo(current.getValue().getNom())<0) {
			addRecursive(current.getLeft(),value);
		} else {
			addRecursive(current.getRight(),value);
		
		}

		return current;
	}
	
	public void add(Stagiaire value) {
	    root = addRecursive(root, value);
	}

	//fin de la mehode pour add
	
	





}
