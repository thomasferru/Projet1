package annuaireIsika.Annuaire.back;

import java.io.Serializable;

/**
 * 
 * cette class contient l'objet StagiaireEtNombreEnfants utiliser dans convertTreeToBinaryFile
 * Aucun interet autre 
 * 
 * @author thoma
 *
 */

public class StagiaireEtNombreEnfants implements Serializable {
	
	@Override
	public String toString() {
		return "StagiaireEtNombreEnfants [stagiaire=" + stagiaire + ", droit=" + droit + ", gauche=" + gauche + "]";
	}
	private Stagiaire stagiaire;
	public int droit;
	public int gauche;
	public Stagiaire getStagiaire() {
		return stagiaire;
	}
	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}
	public int getDroit() {
		return droit;
	}
	public void setDroit(int droit) {
		this.droit = droit;
	}
	public int getGauche() {
		return gauche;
	}
	public void setGauche(int gauche) {
		this.gauche = gauche;
	}
	public StagiaireEtNombreEnfants(Stagiaire stagiaire, int droit, int gauche) {
		super();
		this.stagiaire = stagiaire;
		this.droit = droit;
		this.gauche = gauche;
	}
	
	

}
