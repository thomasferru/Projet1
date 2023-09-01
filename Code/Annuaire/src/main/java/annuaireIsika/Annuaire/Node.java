package annuaireIsika.Annuaire;

public class Node {
	
	//Attributs
	private Stagiaire value;
	private Node left;
	private Node right;
	
	//Constructeur

	public Node(Stagiaire value) {
		this.value=value;
		right=null;
		left=null;
	}

	
	
	// getter setter
	
	public Stagiaire getValue() {
		return value;
	}

	public void setValue(Stagiaire value) {
		this.value = value;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}



	@Override
	public String toString() {
		return "Node [value=" + value + ", left=" + left + ", right=" + right + "]";
	}
	
	
	
	
	
	

}
