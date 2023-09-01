package annuaireIsika.Annuaire;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Stagiaire {

	private String nom;
	private String prenom;
	private int departement;
	private String promotion;
	private int anneeFormation;

	// Contructeur a faire en fonction de valentin

	public Stagiaire(String nom, String prenom, int departement, String promotion, int anneeFormation) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.promotion = promotion;
		this.anneeFormation = anneeFormation;
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

	public int getDepartement() {
		return departement;
	}

	public void setDepartement(int departement) {
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
		return "Stagiaire [nom=" + nom + ", prenom=" + prenom + ", departement=" + departement + ", promotion="
				+ promotion + ", anneeFormation=" + anneeFormation + "]";
	}




	String filePath = "./resources/STAGIAIRES.DON";

	public static List<Stagiaire> loadFromTheFile(String filePath) {
		List<Stagiaire> stagiaires = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			String nom = null;
			String prenom = null;
			int departement = 0;
			String classe = null;
			int anneeRentree = 0;
			while ((line = reader.readLine()) != null) {
				if (line.equals("*")) {
					if (nom != null && prenom != null && departement != 0 && classe != null && anneeRentree != 0) {
						Stagiaire stagiaire = new Stagiaire(nom, prenom, departement, classe, departement);
						stagiaires.add(stagiaire);
					}
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return stagiaires;
	}

}
