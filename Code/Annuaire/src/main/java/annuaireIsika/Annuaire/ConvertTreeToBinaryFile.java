package annuaireIsika.Annuaire;

import java.util.List;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * class permettant de convertir un arbre en fichier binaire,
 *appeler la methode treeToFile, les autres servents juste a faire marcher la premiere
 * @author thoma
 *
 */

public class ConvertTreeToBinaryFile implements Serializable {
	
	private BinaryTree tree;
	
	
	// constructeur
	public ConvertTreeToBinaryFile(BinaryTree tree) {
		super();
		this.tree = tree;
	}



	//methode pour aller d'arbre au format (lucy 1 3 , armand 2 8 , sebastian 5 9 ....)
	
	public ArrayList<StagiaireEtNombreEnfants> treeToListTrie(){

		List listStagiaire = new ArrayList();
		this.tree = fromArrayToTree(Stagiaire.loadFromTheFile());
		ArrayList<StagiaireEtNombreEnfants> result = new ArrayList();
		
		//boucle pour mettre les stagiaires
		
		for (Stagiaire stagiaire : Stagiaire.loadFromTheFile()) {
			StagiaireEtNombreEnfants buffer = new StagiaireEtNombreEnfants(stagiaire,-1,-1);
			result.add(buffer);
		}
		
		// boucle pour mettre les ints dans chaque cases
		
		for (StagiaireEtNombreEnfants aModifier : result) {
			
			aModifier.setDroit(0);
			aModifier.setGauche(0);
			
		}
		
		return result;
		
	}
	
	
	//methode pour trouver les index de chaque fils gauche et droite
	
	public int indexGauche(StagiaireEtNombreEnfants aModifier,ArrayList<StagiaireEtNombreEnfants> result,Node root) {
		
		Stagiaire filsGauche;
		if (this.tree.getRoot().getValue().equals(aModifier.getStagiaire())) {
			filsGauche=this.tree.getRoot().getLeft().getValue();
			return result.indexOf(filsGauche);
			
		} else if (this.tree.getRoot().getValue().getNom().compareTo(aModifier.getStagiaire().getNom())<0) { 
			return indexGauche( aModifier,result,root.getLeft());}
		else if(this.tree.getRoot().getValue().getNom().compareTo(aModifier.getStagiaire().getNom())>0) {
			return indexGauche(aModifier,result,root.getLeft());}
		else {
			return Math.max(indexGauche( aModifier,result,root.getLeft()),indexGauche(aModifier,result,root.getLeft()));
					}
			

		 }
						
	//methode pour trouver les index de chaque fils  droite
	
		public int indexDroit(StagiaireEtNombreEnfants aModifier,ArrayList<StagiaireEtNombreEnfants> result,Node root) {
			
			Stagiaire filsdroit;
			if (this.tree.getRoot().getValue().equals(aModifier.getStagiaire())) {
				filsdroit=this.tree.getRoot().getRight().getValue();
				return result.indexOf(filsdroit);
				
			} else if (this.tree.getRoot().getValue().getNom().compareTo(aModifier.getStagiaire().getNom())>0) { 
				return indexDroit( aModifier,result,root.getRight());}
			else if(this.tree.getRoot().getValue().getNom().compareTo(aModifier.getStagiaire().getNom())<0) {
				return indexDroit(aModifier,result,root.getRight());}
			else {
				return Math.max(indexDroit( aModifier,result,root.getRight()),indexDroit(aModifier,result,root.getRight()));
						}
				

			 }
			
	
		
	

	
	
	// methode pour mettre la liste dans un fichier binaire

	
	public void treeToFile(){
		ConvertTreeToBinaryFile test = new ConvertTreeToBinaryFile((fromArrayToTree(Stagiaire.loadFromTheFile())));
       
		// TODO Auto-generated method stub
		try (FileOutputStream fos = new FileOutputStream("example.bin");
			     ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			
		    oos.writeObject(test.treeToListTrie());
			 fromArrayToTree(Stagiaire.loadFromTheFile());
			} catch (IOException e) {
			    e.printStackTrace();
			}

		
	}
	
	
	
	// je ne sais plus pourquoi, mais c'est important ( j'aurai du commenter avant )
	
	  public BinaryTree fromArrayToTree(List<Stagiaire> stagiaires) {
			BinaryTree result = new BinaryTree((stagiaires.get(0)));
			for (Stagiaire stagiaire : stagiaires) {
				result.getRoot().ajouter(stagiaire);
			}
			return result;
			
		}
}
