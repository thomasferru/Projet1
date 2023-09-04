package annuaireIsika.Annuaire;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
	
	
	
	
	//recherche et renvoie un stagiaire selon son nom
	
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
				return this.right.contient(Nom);
			}
		}
	}
	
	//supprimer un stagiare
	
	public void rechercheNoeudASupprimer(String nom) {
		if (nom.compareTo(this.value.getNom()) < 0) { // je pars à gauche
			if (this.left == null) {
				System.out.println(nom + " ne se trouve pas dans l'arbre");
			} else {
				if (this.left.getValue().getNom().equals(nom)) {
					// Ici, on lancera la supression
					if (this.left.nbDescendants()==0) {
						this.left=null;
					} else if (this.left.nbDescendants() == 2) {
						this.left.supprimerRacineAvecDeuxFils();
					} else if (this.left.getLeft()!=null) {
						this.left=this.left.getLeft();
					}else {
						this.left=this.left.getRight();
					}
					
				} else {
					this.left.rechercheNoeudASupprimer(nom);
				}
			}
		} else { // je pars à droite
			if (this.right == null) {
				System.out.println(nom + " ne se trouve pas dans l'arbre");
			} else {
				if (this.right.getValue().getNom().equals(nom)) {
					// Ici, on lancera la supression
					if (this.right.nbDescendants()==0) {
						this.right=null;
					} else if (this.right.nbDescendants() == 2) {
						//appel d'une méthode de supression
						this.right.supprimerRacineAvecDeuxFils();
					} else if (this.right.getLeft()!=null) {
						this.right=this.right.getLeft();
					}else {
						this.right=this.right.getRight();
					}
				} else {
					this.right.rechercheNoeudASupprimer(nom);
				}
			}
		}
	}public Node rechercheSuccesseur() {
		Node noeudCourant = this.right;
		if (noeudCourant!=null) {
			while (noeudCourant.left!=null) {
				noeudCourant=noeudCourant.left;
			}
		}
		return noeudCourant;
	}public int nbDescendants() {
		// Je suis une feuille -> terminaison
		if ((this.left == null) && (this.right == null)) {
			return 0;
		} else if ((this.left != null) && (this.right == null)) {
			// Je n'ai qu'un fils gauche
			return 1;
		} else if ((this.left == null) && (this.right != null)) {
			// Je n'ai qu'un fils droit
			return 1;
		} else { // j'ai deux fils, je garde donc le maximum entre les deux
			return 2;
		}
	}public void supprimerRacineAvecDeuxFils() {
		this.value=this.rechercheSuccesseur().getValue();
		this.rechercheNoeudASupprimer(value.getNom());
	}

	//modifier un stagiaire
	
	public void modifierStagiaire(String nomSupprimer, Stagiaire stagiaireAjouter) {
		
		rechercheNoeudASupprimer(nomSupprimer);
		ajouter(stagiaireAjouter);
		
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
