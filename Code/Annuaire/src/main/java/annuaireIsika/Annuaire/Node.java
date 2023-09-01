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
	
	
	//ajouter 
	
	public void ajouter(Stagiaire value) {
		if (value.getNom().compareTo(this.value.getNom()) < 0) { // Je pars à gauche ou a droite
			if (this.left == null) { // je regarde si j'ai la place d'insérer à gauche
				this.left = new Node(value);// oui, je créé le noeud
			} else {
				this.left.ajouter(value);// non, je demande au fils Gaudeche de s'en occuper
			}
		} else {
			if (this.right == null) { // je regarde si j'ai la place d'insérer à Droite
				this.right = new Node(value);// oui, je créé le noeud
			} else {
				this.right.ajouter(value);// non, je demande au fils Droit de s'en occuper
			}
		}
	}

	//afficher 

	public String affich() {
		String resultat = "";

		// règle parcours infixe GND
		if (this.left != null) {
			resultat += this.left.toString(); // G
		}

		resultat += " " + this.value; // N

		if (this.right != null) {
			resultat += this.right.toString(); // D
		}

		return resultat;
	}
	
	//recherche et renvoie un stagiaire selon son 
	
	public Stagiaire contient(String Nom) {
		if (this.value.getNom().equals(Nom)) {
			return this.value;
		} else if (Nom.compareTo(this.value.getNom()) < 0) { 
			if (this.left == null) { 
				return null;
			} else {
				return this.left.contient(Nom);
			}
		} else {
			if (this.right == null) { 
				return null;
			} else {
				return this.right.contient(Nom);// non, je demande au fils Droit de s'en occuper
			}
		}
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
		return "[value=" + value + ", left=" + left + ", right=" + right + "]";
	}
	
	
	
	
	
	

}
