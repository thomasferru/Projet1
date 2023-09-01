package annuaireIsika.Annuaire;

public class Laucher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stagiaire paul = new Stagiaire("un","deux",5,"era",5);
		Stagiaire pal = new Stagiaire("u","deux",5,"era",5);
		Stagiaire aul = new Stagiaire("uin","deux",5,"era",5);
		Stagiaire pa = new Stagiaire("ujn","deux",5,"era",5);
		Stagiaire ul = new Stagiaire("uuhhn","deux",5,"era",5);
		BinaryTree test = new BinaryTree(paul);
		test.getRoot().ajouter(pal);
		test.getRoot().ajouter(aul);
		test.getRoot().ajouter(pa);
		test.getRoot().ajouter(ul);
		test.getRoot().rechercheNoeudASupprimer("ujnpo");
		test.getRoot().modifierStagiaire("uuhhn", new Stagiaire("gandalf","le blanc",15,"moria",3015));
		System.out.println(test.getRoot().affich());
		System.out.println(test.getRoot().contient("un"));
		
	}

}
