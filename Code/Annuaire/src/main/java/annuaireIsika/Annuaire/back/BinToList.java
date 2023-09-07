package annuaireIsika.Annuaire.back;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BinToList {

	public BinToList() throws FileNotFoundException {
		super();
		RandomAccessFile raf = new RandomAccessFile("example.bin", "rw");
	}

	/**
	 * Cette méthode crée une observableList a partir du fichier binaire.
	 *
	 *
	 * @return Une observable liste de stagiaire
	 */
	public ObservableList<Stagiaire> binToList() throws IOException {
		RandomAccessFile raf = new RandomAccessFile("example.bin", "rw");
		ObservableList<Stagiaire> stagiairesList = FXCollections.observableArrayList();

		while (raf.getFilePointer() < raf.length()) {
			Node node = litUnNodeDuFichier(raf);
			Stagiaire stagiaire = node.getValue();
			stagiairesList.add(stagiaire);
		}
		raf.close();

		return stagiairesList;
	}

	/**
	 * Cette méthode lit un noeud du fichier.
	 *
	 * @param raf pour voir le pointeur
	 * @return return un node
	 */
	public Node litUnNodeDuFichier(RandomAccessFile raf) throws IOException {
		// le poiteur doit etre au debut de la node
		Node result = new Node(null);
		result.getValue().setNom(readString(raf, Stagiaire.TAILLE_NOM));
		result.getValue().setPrenom(readString(raf, Stagiaire.TAILLE_PRENOM));
		result.getValue().setDepartement(readString(raf, Stagiaire.TAILLE_DPT));
		result.getValue().setPromotion(readString(raf, Stagiaire.TAILLE_PROMO));
		result.getValue().setAnneeFormation(raf.readInt());
		result.setLeft(raf.readInt());
		result.setRight(raf.readInt());
		result.setDoublon(raf.readInt());
		return result;
	}

	/**
	 * Cette méthode lit une chiane de caractere d'un fichier bianire.
	 *
	 * @param raf    pour voir le pointeur
	 * @param length
	 * @return return une String
	 */
	public String readString(RandomAccessFile raf, int length) throws IOException {
		String texte = "";
		for (int i = 0; i < length; i++) {
			texte += raf.readChar();
		}
		return texte;

	}

	public void ajouterUnStagiaireAuFichier(Stagiaire stagiaireAjouter) throws IOException {

		// on doit mettre le pointeur au debut du fichier AVANT la premiere execution
		Node buffer = litUnNodeDuFichier(raf);
		if (buffer.getValue().getNom().compareToIgnoreCase(stagiaireAjouter.getNom()) < 0) {
			if (buffer.getLeft() == -1) {
				raf.seek(raf.getFilePointer() - 12);
				int indexGauche = (int) (raf.length() / buffer.getTaille_Noeud());
				raf.writeInt(indexGauche);
				raf.seek(raf.length());
				ecrireUnNode(stagiaireAjouter, raf);
			} else if (buffer.getLeft() != -1) {
				raf.seek((buffer.getLeft() * buffer.getTaille_Noeud()));
				ajouterUnStagiaireAuFichier(stagiaireAjouter, raf);
			}
		} else if (buffer.getValue().getNom().compareToIgnoreCase(stagiaireAjouter.getNom()) > 0) {
			if (buffer.getRight() == -1) {
				raf.seek(raf.getFilePointer() - 8);
				int indexDroit = (int) (raf.length() / buffer.getTaille_Noeud());
				raf.writeInt(indexDroit);
				raf.seek(raf.length());
				ecrireUnNode(stagiaireAjouter, raf);
			} else if (buffer.getLeft() != -1) {
				raf.seek((buffer.getLeft() * buffer.getTaille_Noeud()));
				ajouterUnStagiaireAuFichier(stagiaireAjouter, raf);
			}
		} else if (buffer.getValue().getNom().compareToIgnoreCase(stagiaireAjouter.getNom()) == 0) {
			if (buffer.getDoublon() == -1) {
				raf.seek(raf.getFilePointer() - 4);
				int indexDouble = (int) (raf.length() / buffer.getTaille_Noeud());
				raf.writeInt(indexDouble);
				raf.seek(raf.length());
				ecrireUnNode(stagiaireAjouter, raf);
			} else if (buffer.getLeft() != -1) {
				raf.seek((buffer.getDoublon() * buffer.getTaille_Noeud()));
				ajouterUnStagiaireAuFichier(stagiaireAjouter, raf);
			}
		}
	}

	public void ecrireUnNode(Stagiaire stagiaireAjout, RandomAccessFile raf) throws IOException {
		raf.writeChars(stagiaireAjout.getNom());
		raf.writeChars(stagiaireAjout.getPrenom());
		raf.writeChars(stagiaireAjout.getDepartement());
		raf.writeChars(stagiaireAjout.getPromotion());
		raf.writeInt(stagiaireAjout.getAnneeFormation());
		raf.writeInt(-1);
		raf.writeInt(-1);
		raf.writeInt(-1);
	}


}
