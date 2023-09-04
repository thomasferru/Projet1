package annuaireIsika.Annuaire;

import java.util.List;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ConvertTreeToBinaryFile implements Serializable {
	
	private BinaryTree tree;
	
	
	
	public ConvertTreeToBinaryFile(BinaryTree tree) {
		super();
		this.tree = tree;
	}



	//methode pour aller d'arbre au format (lucy 1 3 , armand 2 8 , sebastian 5 9 ....)
	
	public ArrayList<StagiaireEtNombreEnfants> treeToListTrie(){

		List listStagiaire = new ArrayList();
		this.tree = fromArrayToTree(Stagiaire.loadFromTheFile());
		ArrayList<StagiaireEtNombreEnfants> result = new ArrayList();
		
		//boucle pour mettre les 1000 stagiaires
		
		for (Stagiaire stagiaire : Stagiaire.loadFromTheFile()) {
			StagiaireEtNombreEnfants buffer = new StagiaireEtNombreEnfants(stagiaire,-1,-1);
			result.add(buffer);
		}
		
		// boucle pour mettre les ints dans chauqe cases
		
		for (StagiaireEtNombreEnfants aModifier : result) {
			
			aModifier.setDroit(0);
			aModifier.setGauche(0);
			
		}
		
		return result;
		
	}
	
	
	
	// methode pour mettre la liste dans un fichier binaire

	
	
	public void treeToFile(){
		Stagiaire.loadFromTheFile();
       
		// TODO Auto-generated method stub
		Stagiaire.loadFromTheFile();
	       
		// TODO Auto-generated method stub
		try (FileOutputStream fos = new FileOutputStream("example.bin");
			     ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			Stagiaire dataArray = new Stagiaire("hery","hery",56,"peor",54); // Example integer array
		    oos.writeObject(dataArray);
			 fromArrayToTree(Stagiaire.loadFromTheFile());
			} catch (IOException e) {
			    e.printStackTrace();
			}

		
	}
	
	
	
	  public BinaryTree fromArrayToTree(List<Stagiaire> stagiaires) {
			BinaryTree result = new BinaryTree((stagiaires.get(0)));
			for (Stagiaire stagiaire : stagiaires) {
				result.getRoot().ajouter(stagiaire);
			}
			return result;
			
		}
}
