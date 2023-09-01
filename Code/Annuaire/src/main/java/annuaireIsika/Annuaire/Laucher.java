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
		test.add(pal);
		test.add(paul);
		test.add(aul);
		test.add(pa);
		test.add(ul);
		System.out.println(test.toString());
	}

}
