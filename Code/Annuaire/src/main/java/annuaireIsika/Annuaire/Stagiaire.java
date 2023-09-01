package annuaireIsika.Annuaire;

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

	public int compareTo(Stagiaire value) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	

	

}
