package annuaireIsika.Annuaire.back;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

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
	 * @throws InterruptedException 
	 */
	public List<Stagiaire> binToList(){
		List<Stagiaire> stagiairesList = new ArrayList<>();
		

		try {
			RandomAccessFile raf = new RandomAccessFile("example.bin", "rw");
			while (raf.getFilePointer() < raf.length()) {
				Node node = litUnNodeDuFichier(raf);
				Stagiaire stagiaire = node.getValue();
				stagiairesList.add(stagiaire);
			}
			raf.close();
			System.out.println(stagiairesList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
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
	// public List<Stagiaire> Recherche (Nom nomChercher){
	// je creer un RandomAcceFiles
	// je creer un lise de stagiaire
	// Je creer un booleans pour savoir quand arreter la boucle et le met en false
	// tant que false
	// je litUnNodeDuFichier avec la methode en haut
	// si le nom (getValue().getNom()) est plus petit que le nomChercher
	// Si la valeur gauche (getValue().getLeft()) est -1
	// je passe le bolleans en true
	// sinon
	// je met la raf a (getValue().getLeft())*176
	// si le nom (getValue().getNom()) est plus grand que le nomChercher
	// Si la valeur droit (getValue().getRight()) est -1
	// je passe le bolleans en true
	// sinon
	// je met la raf a (getValue().getLeft())*176
	// si le nom (getValue().getNom()) est egale au nomChercher
	// je met le stagiaire dans la liste
	// si (getValue().getDouble())!=-1
	// je met la raf a (getValue().getDouble())*176
	// sinon
	// je passe le boleans en true
	// je renvoie la liste

	// }

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
	public List<Stagiaire> rechercheMultiple (String nomChercher,String prenom,String departement,String formation,int annee) throws IOException{
		RandomAccessFile raf = new RandomAccessFile("example.bin", "rw");
		List<Stagiaire> resultats = new ArrayList<>();
		Node noeudCourant = litUnNodeDuFichier(raf);
		while (raf.getFilePointer()<raf.length()) {
		if ((nomChercher!=null)||(nomChercher!=noeudCourant.getValue().getNom())&&(prenom!=null)||(prenom!=noeudCourant.getValue().getPrenom())
				&&(departement!=null)||(departement!=noeudCourant.getValue().getDepartement())&&(formation!=null)||(formation!=noeudCourant.getValue().getPromotion())
				&&(annee!=0)||(annee!=noeudCourant.getValue().getAnneeFormation())) {
			noeudCourant = litUnNodeDuFichier(raf);
		}else {
			resultats.add(noeudCourant.getValue());
			noeudCourant = litUnNodeDuFichier(raf);
		}
		}
		return resultats;
		
	}	
}

	
	
