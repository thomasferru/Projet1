package annuaireIsika.Annuaire;

public class Node {
	
	//Attributs
	private String cle;
	private Node filsGauche;
	private Node filsDroit;
	
	//Constructeur
	public Node(String cle, Node filsGauche, Node filsDroit) {
		this.cle = cle;
		this.filsGauche = null;
		this.filsDroit = null;
	}
	
	// Getters & setters

	public String getCle() {
		return cle;
	}

	public void setCle(String cle) {
		this.cle = cle;
	}

	public Node getFilsGauche() {
		return filsGauche;
	}

	public void setFilsGauche(Node filsGauche) {
		this.filsGauche = filsGauche;
	}

	public Node getFilsDroit() {
		return filsDroit;
	}

	public void setFilsDroit(Node filsDroit) {
		this.filsDroit = filsDroit;
	}
	
	
	
	
	
	

}
