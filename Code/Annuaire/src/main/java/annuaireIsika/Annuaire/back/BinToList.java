package annuaireIsika.Annuaire.back;

import java.io.IOException;
import java.io.RandomAccessFile;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BinToList {
	//private RandomAccessFile rafs;

	public BinToList() throws IOException {
		super();
		//rafs = new RandomAccessFile("example.bin", "rw");
		
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
		Stagiaire buffer = new Stagiaire(null, null, null, null, 0);
		Node result = new Node(buffer);
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

	RandomAccessFile rafs = new RandomAccessFile("example.bin", "rw");
	int tailleFichierAvantAjout = (int) rafs.length();
	while(rafs.length()==tailleFichierAvantAjout) {
		System.out.println(tailleFichierAvantAjout);
		Node buffer = litUnNodeDuFichier(rafs);
		if (buffer.getValue().getNom().compareToIgnoreCase(stagiaireAjouter.getNom()) < 0) {
			if (buffer.getLeft() == -1) {
				rafs.seek(rafs.getFilePointer() - 12);
				int indexGauche = (int) (rafs.length() / buffer.getTaille_Noeud());
				rafs.writeInt(indexGauche);
				rafs.seek(rafs.length());
				ecrireUnNode(stagiaireAjouter, rafs);
			} else if (buffer.getLeft() != -1) {
				rafs.seek((buffer.getLeft() * buffer.getTaille_Noeud()));
				ajouterUnStagiaireAuFichier(stagiaireAjouter);
			}
		} else if (buffer.getValue().getNom().compareToIgnoreCase(stagiaireAjouter.getNom()) > 0) {
			if (buffer.getRight() == -1) {
				rafs.seek(rafs.getFilePointer() - 8);
				int indexDroit = (int) (rafs.length() / buffer.getTaille_Noeud());
				rafs.writeInt(indexDroit);
				rafs.seek(rafs.length());
				ecrireUnNode(stagiaireAjouter, rafs);
			} else if (buffer.getLeft() != -1) {
				rafs.seek((buffer.getLeft() * buffer.getTaille_Noeud()));
				
			}
		} else if (buffer.getValue().getNom().compareToIgnoreCase(stagiaireAjouter.getNom()) == 0) {
			if (buffer.getDoublon() == -1) {
				rafs.seek(rafs.getFilePointer() - 4);
				int indexDouble = (int) (rafs.length() / buffer.getTaille_Noeud());
				rafs.writeInt(indexDouble);
				rafs.seek(rafs.length());
				ecrireUnNode(stagiaireAjouter, rafs);
			} else if (buffer.getLeft() != -1) {
				rafs.seek((buffer.getDoublon() * buffer.getTaille_Noeud()));
			
			}
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
