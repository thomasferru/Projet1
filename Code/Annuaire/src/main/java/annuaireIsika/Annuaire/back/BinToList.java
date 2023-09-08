package annuaireIsika.Annuaire.back;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BinToList {
	// private RandomAccessFile rafs;

	public BinToList() throws IOException {
		super();
		// rafs = new RandomAccessFile("example.bin", "rw");

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
		while (rafs.length() == tailleFichierAvantAjout) {
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

	/**
	 * Cette méthode consiste à rechercher un nom dans la liste et à le retourner je
	 * crée un RandomAcceFiles je crée un liste de stagiaire Je crée un booleans
	 * pour savoir quand arreter la boucle et le met en false tant que false je
	 * litUnNodeDuFichier avec la methode en haut si le nom du noeud courant est
	 * plus petit que le nomChercher Si la valeur gauche est -1 je passe le boolean
	 * à true sinon je mets la raf a (getValue().getLeft())*176 si le nom du noeud
	 * courant est plus grand que le nomChercher Si la valeur droit est -1 je passe
	 * le bolean en true sinon je mets la raf a (getValue().getLeft())*176 si le nom
	 * du noeud courant est egal au nomChercher je mets le stagiaire dans la liste
	 * s'il y a un doublon, je met la raf a (getValue().getDouble())*176 sinon je
	 * passe le boolean à true et je renvoie la liste
	 * 
	 * @param nomChercher
	 * @return une liste
	 * @throws IOException
	 */

	public List<Stagiaire> Rechercher(String nomChercher) throws IOException {

		RandomAccessFile raf = new RandomAccessFile("example.bin", "rw");

		List<Stagiaire> stagiaireRecherche = new ArrayList<>();
		Boolean retour = false;
		while (retour = false) {
			Node newNode = litUnNodeDuFichier(raf);
			if (newNode.getValue().getNom().compareToIgnoreCase(nomChercher) < 0) {
				if (newNode.getLeft() == -1) {
					retour = true;
				} else {
					raf.seek(newNode.getLeft() * 176);
				}
			} else if (newNode.getValue().getNom().compareToIgnoreCase(nomChercher) > 0) {
				if (newNode.getRight() == -1) {
					retour = true;
				} else {
					raf.seek(newNode.getRight() * 176);
				}
			} else if (newNode.getValue().getNom().compareToIgnoreCase(nomChercher) == 0) {
				stagiaireRecherche.add(newNode.getValue());
				if (newNode.getDoublon() == -1) {
					retour = true;
				} else {
					raf.seek(newNode.getDoublon() * 176);
				}
			}
		}

		return stagiaireRecherche;
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
