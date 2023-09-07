package annuaireIsika.Annuaire.back;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Stagiaire implements Serializable{
	public static final int TAILLE_NOM = 22;
	public static final int TAILLE_PRENOM = 20;
	public static final int TAILLE_DPT = 3;
	public static final int TAILLE_PROMO = 12;
	public static final int TAILLE_STAGIAIRE_OCTET = (TAILLE_NOM + TAILLE_PRENOM + TAILLE_DPT + TAILLE_PROMO) *2 + 4;
	
	private String nom;
	private String prenom;
	private String departement;
	private String promotion;
	private int anneeFormation;

	// Contructeur a faire en fonction de valentin

	public Stagiaire(String nom, String prenom, String departement, String promotion, int anneeFormation) {
		super();
		
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.promotion = promotion;
		this.anneeFormation = anneeFormation;
		
	}
	
	public void setStagiaireTailleOctets() {
		while(nom.length()<TAILLE_NOM) {
			nom=nom+" ";}
		while(prenom.length()<TAILLE_PRENOM) {
			prenom=prenom+" ";}
		while(departement.length()<TAILLE_DPT) {
			departement=departement+" ";}
		while(promotion.length()<TAILLE_PROMO) {
			promotion=promotion+" ";}
	}
		
	
	// getter setter

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;

	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;

	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;

	}

	public int getAnneeFormation() {
		return anneeFormation;
	}

	public void setAnneeFormation(int anneeFormation) {
		this.anneeFormation = anneeFormation;

	}

	@Override
	public String toString() {

		return "Stagiaire [nom=" + nom + ", Prenom=" + prenom + ", Departement=" + departement + ", Promotion="
				+ promotion + ", AnneeFormation=" + anneeFormation + "]";

	}

	public int compareTo(Stagiaire value) {
		// TODO Auto-generated method stuby
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Stagiaire))
			return false;
		Stagiaire other = (Stagiaire) obj;
		if (anneeFormation == other.anneeFormation && departement == other.departement
				&& (nom.compareToIgnoreCase(other.nom)==0) && (prenom.compareToIgnoreCase(other.prenom)==0)
				&&(promotion.compareToIgnoreCase(other.promotion)==0)) {
			return true;
		}
		return false;
	}

	public  List<Stagiaire> loadFromTheFile() {
	    // Crée une liste vide pour stocker les objets Stagiaire
	    List<Stagiaire> stagiaires = new ArrayList<>();
	    
	    // Définit le chemin du fichier à lire
	    URL filePath = getClass().getClassLoader().getResource("STAGIAIRES.DON");
	    		//"STAGIAIRES.DON";

	    // Démarre un bloc try-catch pour gérer les exceptions potentielles lors de la lecture du fichier
	    try (BufferedReader reader = new BufferedReader(new FileReader(filePath.getFile()))) {
	        // Déclare une variable pour stocker chaque ligne lue du fichier
	        String line;
	        
	        // Initialise des variables pour stocker les attributs d'un stagiaire
	        String nom = null;
	        String prenom = null;
	        String departement = null;
	        String classe = null;
	        int anneeRentree = 0;

	        // Démarre une boucle while pour lire chaque ligne du fichier
	        while ((line = reader.readLine()) != null) {
	            // Vérifie si la ligne actuelle est égale à "*". Si c'est le cas, cela signifie qu'un nouveau stagiaire commence.
	        	if (line.equals("*")) {
	        	    // Vérifie si toutes les informations nécessaires pour créer un stagiaire sont disponibles.
	        	    if (nom != null && prenom != null && departement != null && classe != null && anneeRentree != 0) {
	        	        // Crée un nouvel objet Stagiaire avec les informations lues et l'ajoute à la liste.
	        	        Stagiaire stagiaire = new Stagiaire(nom, prenom, departement, classe, anneeRentree);
	        	        stagiaire.setStagiaireTailleOctets();
	        	        stagiaires.add(stagiaire);
	        	    }
	        	    // Réinitialise les variables pour les attributs du prochain stagiaire
	        	    nom = null;
	        	    prenom = null;
	        	    departement = null;
	        	    classe = null;
	        	    anneeRentree = 0;
	        	} else {
	        	    // Sinon, la ligne contient un attribut du stagiaire en cours de lecture
	        	    if (nom == null) {
	        	        nom = line;
	        	    } else if (prenom == null) {
	        	        prenom = line;
	        	    } else if (departement == null) {
	        	        // Si departement est 0, cela signifie que nous n'avons pas encore lu cette information
	        	        departement = line;
	        	    } else if (classe == null) {
	        	        classe = line;
	        	    } else {
	        	        // Si anneeRentree est 0, cela signifie que nous n'avons pas encore lu cette information
	        	        anneeRentree = Integer.parseInt(line);
	        	    }
	        	}
	        }
	    } catch (Exception e) {
	        // Gère toute exception qui pourrait se produire lors de la lecture du fichier (cette partie doit être remplie en fonction de votre gestion d'erreurs).
	    }
	    
	    // Retourne la liste des stagiaires après avoir parcouru tout le fichier
	    return stagiaires;
	}

}
