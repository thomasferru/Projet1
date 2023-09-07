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

public class Node implements Serializable {

	public static final int TAILLE_NOEUD = Stagiaire.TAILLE_STAGIAIRE_OCTET + 3*4;
	// Attributs
	private Stagiaire value;
	private int left;
	private int right;
	private int doublon;

	// Constructeur

	public Node(Stagiaire value) {
		this.value = value;
		right = -1;
		left = -1;
		doublon = -1;
	}

	// ajouter

	/*
	 * public void ajouter(Stagiaire value) { if
	 * (value.getNom().compareTo(this.value.getNom()) < 0) { // Je pars à gauche ou
	 * a droite if (this.left == null) { // je regarde si j'ai la place d'insérer à
	 * gauche this.left = new Node(value);// oui, je créé le noeud } else {
	 * this.left.ajouter(value);// non, je demande au fils Gaudeche de s'en occuper
	 * } } else { if (this.right == null) { // je regarde si j'ai la place d'insérer
	 * à Droite this.right = new Node(value);// oui, je créé le noeud } else {
	 * this.right.ajouter(value);// non, je demande au fils Droit de s'en occuper }
	 * } }
	 */

	public void ajouterBinaire(Stagiaire stagiaireAjout, RandomAccessFile raf) throws IOException {

		// int comparaison = this.nom.CompareTo(stagiaire.nom)

		if (stagiaireAjout.getNom().compareToIgnoreCase(this.getValue().getNom()) < 0) {
			
			if (this.left == -1) {

				int indexFilsGauche = (int) (raf.length() / TAILLE_NOEUD);
				raf.seek(raf.getFilePointer() - 12);
				raf.writeInt(indexFilsGauche);

				raf.seek(raf.length());

				// on ecrit le stagiaire a la fin du fichier binaire

				raf.writeChars(stagiaireAjout.getNom());

				raf.writeChars(stagiaireAjout.getPrenom());

				raf.writeChars(stagiaireAjout.getDepartement());

				raf.writeChars(stagiaireAjout.getPromotion());

				raf.writeInt(stagiaireAjout.getAnneeFormation());

				raf.writeInt(-1);

				raf.writeInt(-1);

				raf.writeInt(-1);

			} else {
				raf.seek(this.left * TAILLE_NOEUD);

				Stagiaire buffer = new Stagiaire(null, null, null, null, 0);
				Node noeudGauche = new Node(buffer);

				noeudGauche.getValue().setNom(readString(raf, Stagiaire.TAILLE_NOM));
				noeudGauche.getValue().setPrenom(readString(raf, Stagiaire.TAILLE_PRENOM));
				noeudGauche.getValue().setDepartement(readString(raf, Stagiaire.TAILLE_DPT));
				noeudGauche.getValue().setPromotion(readString(raf, Stagiaire.TAILLE_PROMO));
				noeudGauche.getValue().setAnneeFormation(raf.readInt());
				noeudGauche.setLeft(raf.readInt());
				noeudGauche.setRight(raf.readInt());
				noeudGauche.setDoublon(raf.readInt());

				noeudGauche.ajouterBinaire(stagiaireAjout, raf);

			}

		} else if (stagiaireAjout.getNom().compareToIgnoreCase(this.getValue().getNom()) > 0) {
			
			if (this.right == -1) {

				int indexFilsDroit = (int) (raf.length() / TAILLE_NOEUD);
				raf.seek(raf.getFilePointer() - 8);
				raf.writeInt(indexFilsDroit);
				raf.seek(raf.length());

				// on ecrit le stagiaire a la fin du fichier binaire
				raf.writeChars(stagiaireAjout.getNom());

				raf.writeChars(stagiaireAjout.getPrenom());

				raf.writeChars(stagiaireAjout.getDepartement());

				raf.writeChars(stagiaireAjout.getPromotion());

				raf.writeInt(stagiaireAjout.getAnneeFormation());

				raf.writeInt(-1);

				raf.writeInt(-1);

				raf.writeInt(-1);

				
			} else {

				raf.seek(this.right * TAILLE_NOEUD);
				Stagiaire buffer = new Stagiaire(null, null, null, null, 0);
				Node noeudDroit = new Node(buffer);
				noeudDroit.getValue().setNom(readString(raf, Stagiaire.TAILLE_NOM));
				noeudDroit.getValue().setPrenom(readString(raf, Stagiaire.TAILLE_PRENOM));
				noeudDroit.getValue().setDepartement(readString(raf, Stagiaire.TAILLE_DPT));
				noeudDroit.getValue().setPromotion(readString(raf, Stagiaire.TAILLE_PROMO));
				noeudDroit.getValue().setAnneeFormation(raf.readInt());
				noeudDroit.setLeft(raf.readInt());
				noeudDroit.setRight(raf.readInt());
				noeudDroit.setDoublon(raf.readInt());

				noeudDroit.ajouterBinaire(stagiaireAjout, raf);

			}
		} else if (stagiaireAjout.getNom().compareToIgnoreCase(this.getValue().getNom()) == 0) {

			
			if (this.doublon == -1) {

				int indexFilsDoublon = (int) (raf.length() / TAILLE_NOEUD);
				raf.seek(raf.getFilePointer() -4);
				raf.writeInt(indexFilsDoublon);
				raf.seek(raf.length());
				// on ecrit le stagiaire a la fin du fichier binaire
				raf.writeChars(stagiaireAjout.getNom());

				raf.writeChars(stagiaireAjout.getPrenom());

				raf.writeChars(stagiaireAjout.getDepartement());

				raf.writeChars(stagiaireAjout.getPromotion());

				raf.writeInt(stagiaireAjout.getAnneeFormation());

				raf.writeInt(-1);

				raf.writeInt(-1);

				raf.writeInt(-1);

			} else {

				raf.seek(this.doublon * TAILLE_NOEUD);
				Stagiaire buffer = new Stagiaire(null, null, null, null, 0);
				Node noeudDouble = new Node(buffer);
				noeudDouble.getValue().setNom(readString(raf, Stagiaire.TAILLE_NOM));
				noeudDouble.getValue().setPrenom(readString(raf, Stagiaire.TAILLE_PRENOM));
				noeudDouble.getValue().setDepartement(readString(raf, Stagiaire.TAILLE_DPT));
				noeudDouble.getValue().setPromotion(readString(raf, Stagiaire.TAILLE_PROMO));
				noeudDouble.getValue().setAnneeFormation(raf.readInt());
				noeudDouble.setLeft(raf.readInt());
				noeudDouble.setRight(raf.readInt());
				noeudDouble.setDoublon(raf.readInt());
				

				noeudDouble.ajouterBinaire(stagiaireAjout, raf);

			}

		}

		// si comparaison > 0 -> à gauche
		// si this.filsGauche <0
		// on a trouvé l'emplacement
		// on remonte le curseur de 8octet ->raf.seek(raf.getfilePointer -12)
		// on, ecrit l'index du stagiaire qu'on ajoute : taille du fichier / taille d'un
		// noed (en octet)
		// on se met à la fin du fichier -> raf.seek(raf.length)
		// j'ecris le noeudAjout
		// sinon on va appeler la recursivité depuis le fils gauche
		// je met le curseur à indexFilsFauche * Taille Noeud
		// je lis le noeud Gauche et le stocke dans une variable
		// gauche.ajouter(stagiaireAjout)
		// sinon si comparaison <0 -> a droite
		// meme raisonnement que pour gauche
		// attention on remonte de -8
		// sinon comparaison = 0
		// meme raisonnement mais on remonte de -4
	}

	public String readString(RandomAccessFile raf, int length) throws IOException {
		String texte = "";
		for (int i = 0; i < length; i++) {
			texte += raf.readChar();
		}
		return texte;

	}

	// afficher

	/*
	 * public String affich() { String resultat = "";
	 * 
	 * // règle parcours infixe GND if (this.left != null) { resultat +=
	 * this.left.toString(); // G }
	 * 
	 * resultat += " " + this.value; // N
	 * 
	 * if (this.right != null) { resultat += this.right.toString(); // D }
	 * 
	 * return resultat; }
	 * 
	 * // recherche et renvoie un stagiaire selon son nom
	 * 
	 * public Stagiaire contient(String Nom) { if (this.value.getNom().equals(Nom))
	 * { return this.value; } else if (Nom.compareTo(this.value.getNom()) < 0) { if
	 * (this.left == null) { return null; } else { return this.left.contient(Nom); }
	 * } else { if (this.right == null) { return null; } else { return
	 * this.right.contient(Nom); } } }
	 * 
	 * // supprimer un stagiare
	 * 
	 * public void rechercheNoeudASupprimer(String nom) { if
	 * (nom.compareTo(this.value.getNom()) < 0) { // je pars à gauche if (this.left
	 * == null) { System.out.println(nom + " ne se trouve pas dans l'arbre"); } else
	 * { if (this.left.getValue().getNom().equals(nom)) { // Ici, on lancera la
	 * supression if (this.left.nbDescendants() == 0) { this.left = null; } else if
	 * (this.left.nbDescendants() == 2) { this.left.supprimerRacineAvecDeuxFils(); }
	 * else if (this.left.getLeft() != null) { this.left = this.left.getLeft(); }
	 * else { this.left = this.left.getRight(); }
	 * 
	 * } else { this.left.rechercheNoeudASupprimer(nom); } } } else { // je pars à
	 * droite if (this.right == null) { System.out.println(nom +
	 * " ne se trouve pas dans l'arbre"); } else { if
	 * (this.right.getValue().getNom().equals(nom)) { // Ici, on lancera la
	 * supression if (this.right.nbDescendants() == 0) { this.right = null; } else
	 * if (this.right.nbDescendants() == 2) { // appel d'une méthode de supression
	 * this.right.supprimerRacineAvecDeuxFils(); } else if (this.right.getLeft() !=
	 * null) { this.right = this.right.getLeft(); } else { this.right =
	 * this.right.getRight(); } } else { this.right.rechercheNoeudASupprimer(nom); }
	 * } } }
	 * 
	 * public Node rechercheSuccesseur() { Node noeudCourant = this.right; if
	 * (noeudCourant != null) { while (noeudCourant.left != null) { noeudCourant =
	 * noeudCourant.left; } } return noeudCourant; }
	 * 
	 * public int nbDescendants() { // Je suis une feuille -> terminaison if
	 * ((this.left == null) && (this.right == null)) { return 0; } else if
	 * ((this.left != null) && (this.right == null)) { // Je n'ai qu'un fils gauche
	 * return 1; } else if ((this.left == null) && (this.right != null)) { // Je
	 * n'ai qu'un fils droit return 1; } else { // j'ai deux fils, je garde donc le
	 * maximum entre les deux return 2; } }
	 * 
	 * public void supprimerRacineAvecDeuxFils() { this.value =
	 * this.rechercheSuccesseur().getValue();
	 * this.rechercheNoeudASupprimer(value.getNom()); }
	 * 
	 * // modifier un stagiaire
	 * 
	 * public void modifierStagiaire(String nomSupprimer, Stagiaire
	 * stagiaireAjouter) {
	 * 
	 * rechercheNoeudASupprimer(nomSupprimer); ajouter(stagiaireAjouter);
	 * 
	 * }
	 */
	
	
	

	// getter setter

	public Stagiaire getValue() {
		return value;
	}

	public void setValue(Stagiaire value) {
		this.value = value;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public int getDoublon() {
		return doublon;
	}

	public void setDoublon(int doublon) {
		this.doublon = doublon;
	}
	public int getTaille_Noeud() {
		return this.TAILLE_NOEUD;
	}

	@Override
	public String toString() {
		return "[value=" + value + ", left=" + left + ", right=" + right + "]";
	}

}
