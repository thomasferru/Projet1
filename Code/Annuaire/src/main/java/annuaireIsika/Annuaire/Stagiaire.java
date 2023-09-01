package annuaireIsika.Annuaire;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Stagiaire {
	
	private String nom;
	private String Prenom;
	private int Departement;
	private String Promotion;
	private int AnneeFormation;
	
	
	// Contructeur a faire en fonction de valentin
	
	public Stagiaire(String nom, String prenom, int departement, String promotion, int anneeFormation) {
		super();
		this.nom = nom;
		Prenom = prenom;
		Departement = departement;
		Promotion = promotion;
		AnneeFormation = anneeFormation;
	}
	
	// getter setter
	
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return Prenom;
	}
	public void setPrenom(String prenom) {
		Prenom = prenom;
	}
	public int getDepartement() {
		return Departement;
	}
	public void setDepartement(int departement) {
		Departement = departement;
	}
	public String getPromotion() {
		return Promotion;
	}
	public void setPromotion(String promotion) {
		Promotion = promotion;
	}
	public int getAnneeFormation() {
		return AnneeFormation;
	}
	public void setAnneeFormation(int anneeFormation) {
		AnneeFormation = anneeFormation;
	}

	@Override
	public String toString() {
		return "Stagiaire [nom=" + nom + ", Prenom=" + Prenom + ", Departement=" + Departement + ", Promotion="
				+ Promotion + ", AnneeFormation=" + AnneeFormation + "]";
	}
	
    public void ParcoursDon() {
    	/*
		 * lit le contenu d'un fichier ligne par ligne, crée un Label pour chaque ligne lue,
		 * puis ajoute chaque Label à une VBox pour afficher les lignes du fichier dans 
		 * l'interface utilisateur JavaFX. Il gère également les exceptions liées à la lecture
		 * du fichier en les imprimant dans la console. 
		  
		 */
		
		
        try (BufferedReader reader = new BufferedReader(new FileReader("./resources/STAGIAIRES.DON"))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
            	System.out.println(line);
            	
            	if (line.contains("*")){
            		 break;
            	}

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	
//	public static List<Stagiaire>loadFromTheFile
	
	

	

}
