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

	}

	public String readString(RandomAccessFile raf, int length) throws IOException {
		String texte = "";
		for (int i = 0; i < length; i++) {
			texte += raf.readChar();
		}
		return texte;

	}


	

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
