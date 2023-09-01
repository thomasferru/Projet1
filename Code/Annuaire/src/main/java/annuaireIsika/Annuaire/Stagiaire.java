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

		return "Stagiaire [nom=" + nom + ", Prenom=" + prenom + ", Departement=" + departement + ", Promotion="
				+ promotion + ", AnneeFormation=" + anneeFormation + "]";

	}

	public int compareTo(Stagiaire value) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	public static List<Stagiaire> loadFromTheFile() {
		List<Stagiaire> stagiaires = new ArrayList<>();
		String filePath = "./resources/STAGIAIRES.DON";

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
						Stagiaire stagiaire = new Stagiaire(nom, prenom, departement, classe, anneeRentree);
						stagiaires.add(stagiaire);

					}
					nom = null;
					prenom = null;
					departement = 0;
					classe = null;
					anneeRentree = 0;
				} else {
					// Sinon, lisez les attributs du stagiaire
					if (nom == null) {
						nom = line;
					} else if (prenom == null) {
						prenom = line;
					} else if (departement == 0) {
						departement = Integer.parseInt(line);
					} else if (classe == null) {
						classe = line;
					} else {
						anneeRentree = Integer.parseInt(line);
					}

					if (nom != null && prenom != null && departement != 0 && classe != null && anneeRentree != 0) {
						Stagiaire stagiaire = new Stagiaire(nom, prenom, departement, classe, anneeRentree);
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
