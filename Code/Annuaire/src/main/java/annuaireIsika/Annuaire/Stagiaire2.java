import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Stagiaire2 {
	private StringProperty nom;
	private StringProperty prenom;
	private IntegerProperty departement;
	private StringProperty promotion;
	private IntegerProperty anneeFormation;

	public Stagiaire2(String nom, String prenom, int departement, String promotion, int anneeFormation) {
		this.nom = new SimpleStringProperty(nom);
		this.prenom = new SimpleStringProperty(prenom);
		this.departement = new SimpleIntegerProperty(departement);
		this.promotion = new SimpleStringProperty(promotion);
		this.anneeFormation = new SimpleIntegerProperty(anneeFormation);
	}

	// Getters et setters pour les propriétés observables

	public String getNom() {
		return nom.get();
	}

	public void setNom(String nom) {
		this.nom.set(nom);
	}

	public StringProperty nomProperty() {
		return nom;
	}

	public String getPrenom() {
		return prenom.get();
	}

	public void setPrenom(String prenom) {
		this.prenom.set(prenom);
	}

	public StringProperty prenomProperty() {
		return prenom;
	}

	public int getDepartement() {
		return departement.get();
	}

	public void setDepartement(int departement) {
		this.departement.set(departement);
	}

	public IntegerProperty departementProperty() {
		return departement;
	}

	public String getPromotion() {
		return promotion.get();
	}

	public void setPromotion(String promotion) {
		this.promotion.set(promotion);
	}

	public StringProperty promotionProperty() {
		return promotion;
	}

	public int getAnneeFormation() {
		return anneeFormation.get();
	}

	public void setAnneeFormation(int anneeFormation) {
		this.anneeFormation.set(anneeFormation);
	}

	public IntegerProperty anneeFormationProperty() {
		return anneeFormation;
	}

	@Override
	public String toString() {
		return "Stagiaire2 [nom=" + nom + ", prenom=" + prenom + ", departement=" + departement + ", promotion="
				+ promotion + ", anneeFormation=" + anneeFormation + "]";
	}
	
	
	
	//-----------------------------------
	
	public static List<Stagiaire> loadFromTheFile() {
	    // Crée une liste vide pour stocker les objets Stagiaire
	    List<Stagiaire> stagiaires = new ArrayList<>();
	    
	    // Définit le chemin du fichier à lire
	    String filePath = "./resources/STAGIAIRES.DON";

	    // Démarre un bloc try-catch pour gérer les exceptions potentielles lors de la lecture du fichier
	    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	        // Déclare une variable pour stocker chaque ligne lue du fichier
	        String line;
	        
	        // Initialise des variables pour stocker les attributs d'un stagiaire
	        String nom = null;
	        String prenom = null;
	        int departement = 0;
	        String classe = null;
	        int anneeRentree = 0;

	        // Démarre une boucle while pour lire chaque ligne du fichier
	        while ((line = reader.readLine()) != null) {
	            // Vérifie si la ligne actuelle est égale à "*". Si c'est le cas, cela signifie qu'un nouveau stagiaire commence.
	        	if (line.equals("*")) {
	        	    // Vérifie si toutes les informations nécessaires pour créer un stagiaire sont disponibles.
	        	    if (nom != null && prenom != null && departement != 0 && classe != null && anneeRentree != 0) {
	        	        // Crée un nouvel objet Stagiaire avec les informations lues et l'ajoute à la liste.
	        	        Stagiaire stagiaire = new Stagiaire(nom, prenom, departement, classe, anneeRentree);
	        	        stagiaires.add(stagiaire);
	        	    }
	        	    // Réinitialise les variables pour les attributs du prochain stagiaire
	        	    nom = null;
	        	    prenom = null;
	        	    departement = 0;
	        	    classe = null;
	        	    anneeRentree = 0;
	        	} else {
	        	    // Sinon, la ligne contient un attribut du stagiaire en cours de lecture
	        	    if (nom == null) {
	        	        nom = line;
	        	    } else if (prenom == null) {
	        	        prenom = line;
	        	    } else if (departement == 0) {
	        	        // Si departement est 0, cela signifie que nous n'avons pas encore lu cette information
	        	        departement = Integer.parseInt(line);
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
