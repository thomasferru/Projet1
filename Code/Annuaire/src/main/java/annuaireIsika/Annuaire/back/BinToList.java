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
	public List<Stagiaire> binToList() {
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
		result.getValue().setNom(readString(raf, Stagiaire.TAILLE_NOM).trim());
		result.getValue().setPrenom(readString(raf, Stagiaire.TAILLE_PRENOM).trim());
		result.getValue().setDepartement(readString(raf, Stagiaire.TAILLE_DPT).trim());
		result.getValue().setPromotion(readString(raf, Stagiaire.TAILLE_PROMO).trim());
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
				} else if (buffer.getRight() != -1) {
					rafs.seek((buffer.getRight() * buffer.getTaille_Noeud()));

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

	public List<Stagiaire> Rechercher(String nomChercher) throws IOException {
		int TAILLE_NOEUD_EN_OCTET = 130;
		RandomAccessFile raf = new RandomAccessFile("example.bin", "rw");
		List<Stagiaire> stagiaireRecherche = new ArrayList<>();
		Boolean retour = false;
		while (retour == false) {
			Node newNode = litUnNodeDuFichier(raf);
			if (newNode.getValue().getNom().compareToIgnoreCase(nomChercher) > 0) {
				if (newNode.getLeft() == -1) {
					retour = true;
				} else {

					raf.seek(newNode.getLeft() * TAILLE_NOEUD_EN_OCTET);
				}
			} else if (newNode.getValue().getNom().compareToIgnoreCase(nomChercher) < 0) {
				if (newNode.getRight() == -1) {
					retour = true;
				} else {
					raf.seek(newNode.getRight() * TAILLE_NOEUD_EN_OCTET);
				}
			} else if (newNode.getValue().getNom().compareToIgnoreCase(nomChercher) == 0) {
				stagiaireRecherche.add(newNode.getValue());
				if (newNode.getDoublon() == -1) {
					retour = true;
				} else {

					raf.seek(newNode.getDoublon() * TAILLE_NOEUD_EN_OCTET);
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

	public List<Stagiaire> rechercheMultiple(String nomChercher, String prenom, String departement, String formation,
			int annee) throws IOException {
		RandomAccessFile raf = new RandomAccessFile("example.bin", "rw");
		List<Stagiaire> resultats = new ArrayList<>();
		Node noeudCourant = litUnNodeDuFichier(raf);
		String nomVide = "";
		String prenomVIde = "";
		String depVide = "";
		String promoVode = "";
		while (raf.getFilePointer() < raf.length()) {
			if (((nomChercher.compareTo(nomVide) == 0)
					|| (nomChercher.compareTo(noeudCourant.getValue().getNom())) == 0)
					&& ((prenom.compareTo(prenomVIde) == 0)
							|| (prenom.compareTo(noeudCourant.getValue().getPrenom())) == 0)
					&& ((departement.compareTo(depVide) == 0)
							|| (departement.compareTo(noeudCourant.getValue().getDepartement())) == 0)
					&& ((formation.compareTo(promoVode) == 0)
							|| (formation.compareTo(noeudCourant.getValue().getPromotion())) == 0)
					&& ((annee == 0) || (annee == noeudCourant.getValue().getAnneeFormation()))) {
				resultats.add(noeudCourant.getValue());
				noeudCourant = litUnNodeDuFichier(raf);
			} else {
				noeudCourant = litUnNodeDuFichier(raf);
			}
		}
		return resultats;

	}

	public void modificationStagiaire(Stagiaire stagiaireAmodifier, Stagiaire stagiaireModifier) throws IOException {
		int TAILLE_NOEUD = 130;
		
		RandomAccessFile raf = new RandomAccessFile("example.bin", "rw");
		Node node = litUnNodeDuFichier(raf);
	
		

		while (raf.getFilePointer() < raf.length()) {
			
			if (node.getValue().getNom().equals((stagiaireAmodifier).getNom())&&(node.getValue().getPromotion().equals((stagiaireAmodifier).getPromotion()))) {
				System.out.println("ici");
				raf.seek(raf.getFilePointer()- TAILLE_NOEUD);
				stagiaireModifier.setStagiaireTailleOctets();
				ecrireUnNode(stagiaireModifier, raf);
				break;

	
			}else {
				node = litUnNodeDuFichier(raf);
				System.out.println(node);


			}
			
		}

		raf.close();
	}

}
