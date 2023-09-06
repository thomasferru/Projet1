package annuaireIsika.Annuaire.back;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Node implements Serializable{

	// Attributs
	private Stagiaire value;
	private Node left;
	private Node right;

	// Constructeur

	public Node(Stagiaire value) {
		this.value = value;
		right = null;
		left = null;
	}

	// ajouter

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
	
	public void ajouterBinaire(Stagiaire stagiaireAjout,RandomAccessFile raf) throws IOException {
		
		
		
		//int comparaison =  this.nom.CompareTo(stagiaire.nom)
		
		if (stagiaireAjout.getNom().compareToIgnoreCase(this.getValue().getNom())<0) {
			raf.seek(raf.getFilePointer()-12);
			//raf.seek(raf.getFilePointer()+12);
			int indexExistantGauche =raf.readInt();
			raf.seek(raf.getFilePointer()-4);
			
			if(indexExistantGauche == -1) {
				
				int indexFilsGauche = (int) raf.length()/96;
				raf.writeInt(indexFilsGauche);
				
				//on ecrit le stagiaire a la fin du fichier binaire
				raf.seek(raf.length());
				raf.writeUTF(stagiaireAjout.getNom());
	            raf.writeUTF(stagiaireAjout.getPrenom());
	            raf.writeUTF(stagiaireAjout.getDepartement());
	            raf.writeUTF(stagiaireAjout.getPromotion());
	            raf.writeInt(stagiaireAjout.getAnneeFormation());
	            raf.writeInt(-1);
	            raf.writeInt(-1);
	            raf.writeInt(-1);
	            
				

	            
			}else {
				
				raf.seek(indexExistantGauche*96);
				Node noeudGauche = new Node(stagiaireAjout);
				noeudGauche.getValue().setNom(readString(raf,20));
				noeudGauche.getValue().setPrenom(readString(raf,20));
				noeudGauche.getValue().setDepartement(readString(raf,20));
				noeudGauche.getValue().setPromotion(readString(raf,20));
				noeudGauche.getValue().setAnneeFormation(raf.readInt());
				noeudGauche.ajouterBinaire(stagiaireAjout, raf);
				
			}
			
		}else if (stagiaireAjout.getNom().compareToIgnoreCase(this.getValue().getNom())>0) {
				raf.seek(raf.length()-8);
				
				
				int indexExistantDroit =raf.readInt();
				
				if(indexExistantDroit == -1) {
					
					int indexFilsDroit = (int) raf.length()/96;
					raf.writeInt(indexFilsDroit);
					
					//on ecrit le stagiaire a la fin du fichier binaire
					raf.seek(raf.length());
					raf.writeUTF(stagiaireAjout.getNom());
		            raf.writeUTF(stagiaireAjout.getPrenom());
		            raf.writeUTF(stagiaireAjout.getDepartement());
		            raf.writeUTF(stagiaireAjout.getPromotion());
		            raf.writeInt(stagiaireAjout.getAnneeFormation());
		            raf.writeInt(-1);
		            raf.writeInt(-1);
		            raf.writeInt(-1);
				}else {
					
					raf.seek(indexExistantDroit*96);
					Node noeudDroit = new Node(stagiaireAjout);
					noeudDroit.getValue().setNom(readString(raf,20));
					noeudDroit.getValue().setPrenom(readString(raf,20));
					noeudDroit.getValue().setDepartement(readString(raf,20));
					noeudDroit.getValue().setPromotion(readString(raf,20));
					noeudDroit.getValue().setAnneeFormation(raf.readInt());
					raf.seek(raf.getFilePointer()+12);
					noeudDroit.ajouterBinaire(stagiaireAjout, raf);
					
				}
		
			}
		
		//si comparaison > 0 -> à gauche
			//si this.filsGauche <0
				//on a trouvé l'emplacement
				//on remonte le curseur de 8octet ->raf.seek(raf.getfilePointer -12)
				//on, ecrit l'index du stagiaire qu'on ajoute : taille du fichier / taille d'un noed (en octet)
				// on se met à la fin du fichier -> raf.seek(raf.length)
				//j'ecris le noeudAjout 
			//sinon on va appeler la recursivité depuis le fils gauche
				//je met le curseur à indexFilsFauche * Taille Noeud
				//je lis le noeud Gauche et le stocke dans une variable
				//gauche.ajouter(stagiaireAjout)
		//sinon si comparaison <0 -> a droite
			//meme raisonnement que pour gauche 
			// attention on remonte de -8
		//sinon comparaison = 0 
			//meme raisonnement mais on remonte de -4
		}
		
	
	 private static String readString(RandomAccessFile raf, int length) throws IOException {
         byte[] buffer = new byte[length];
         int bytesRead = raf.read(buffer);

         if (bytesRead != -1) {
             return new String(buffer, "UTF-8"); // Adjust encoding if necessary
         } else {
             return null;
         
     }

}

	// afficher

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

	// recherche et renvoie un stagiaire selon son nom

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

	// supprimer un stagiare

	public void rechercheNoeudASupprimer(String nom) {
		if (nom.compareTo(this.value.getNom()) < 0) { // je pars à gauche
			if (this.left == null) {
				System.out.println(nom + " ne se trouve pas dans l'arbre");
			} else {
				if (this.left.getValue().getNom().equals(nom)) {
					// Ici, on lancera la supression
					if (this.left.nbDescendants() == 0) {
						this.left = null;
					} else if (this.left.nbDescendants() == 2) {
						this.left.supprimerRacineAvecDeuxFils();
					} else if (this.left.getLeft() != null) {
						this.left = this.left.getLeft();
					} else {
						this.left = this.left.getRight();
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
					if (this.right.nbDescendants() == 0) {
						this.right = null;
					} else if (this.right.nbDescendants() == 2) {
						// appel d'une méthode de supression
						this.right.supprimerRacineAvecDeuxFils();
					} else if (this.right.getLeft() != null) {
						this.right = this.right.getLeft();
					} else {
						this.right = this.right.getRight();
					}
				} else {
					this.right.rechercheNoeudASupprimer(nom);
				}
			}
		}
	}

	public Node rechercheSuccesseur() {
		Node noeudCourant = this.right;
		if (noeudCourant != null) {
			while (noeudCourant.left != null) {
				noeudCourant = noeudCourant.left;
			}
		}
		return noeudCourant;
	}

	public int nbDescendants() {
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
	}

	public void supprimerRacineAvecDeuxFils() {
		this.value = this.rechercheSuccesseur().getValue();
		this.rechercheNoeudASupprimer(value.getNom());
	}

	// modifier un stagiaire

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
