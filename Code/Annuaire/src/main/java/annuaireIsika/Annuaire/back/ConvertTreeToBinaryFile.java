package annuaireIsika.Annuaire.back;

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
			StagiaireEtNombreEnfants buffer = new StagiaireEtNombreEnfants(stagiaire,0,0);
			result.add(buffer);
		}
		
		// boucle pour mettre les ints dans chaque cases
		
		for (StagiaireEtNombreEnfants StagiaireEtIntaModifier : result) {
			
			
			StagiaireEtIntaModifier.setGauche(indexGauche(this.tree.getRoot(), StagiaireEtIntaModifier.getStagiaire(),result));
			StagiaireEtIntaModifier.setDroit(indexDroit(this.tree.getRoot(), StagiaireEtIntaModifier.getStagiaire(),result));
			
			
		}
		
		return result;
		
	}
	
	
	//methode pour trouver les index de chaque fils gauche et droite
	
	public BinaryTree getTree() {
		return tree;
	}



	public void setTree(BinaryTree tree) {
		this.tree = tree;
	}


	public int indexGauche(Node root,Stagiaire stagiaire,ArrayList<StagiaireEtNombreEnfants> listStagiaire) {
		int i =-1;
		
			if ((root!=null)&&(stagiaire.equals(root.getValue()))) {
			for(StagiaireEtNombreEnfants filsGauche :listStagiaire) {
				i=i+1;
				if(((root.getLeft())!=null)&&(root.getLeft().getValue().equals(filsGauche.getStagiaire()))) {
					
					return i;
				}
				
				
			}return i=-1;
			}
			
			else if(((root!=null)&&(stagiaire.getNom().compareToIgnoreCase(root.getValue().getNom()) ==0))) {
				// probleme, pourquoi sa return 0 ???
				
				int leftMax = indexGauche(root.getLeft(), stagiaire, listStagiaire);
				int rightMax = indexGauche(root.getRight(), stagiaire, listStagiaire);
              	return Math.max(rightMax, leftMax);
              	
		
		}else if((root!=null)&&(stagiaire.getNom().compareToIgnoreCase(root.getValue().getNom())<0)) {
			return indexGauche(root.getLeft(),stagiaire,listStagiaire);
				
		}else if((root!=null)&&(stagiaire.getNom().compareToIgnoreCase(root.getValue().getNom())>0)) {
			return indexGauche(root.getRight(),stagiaire,listStagiaire);
		}

		
		return 0;

		 }


		
	
						
	//methode pour trouver les index de chaque fils  droite
	
		public int indexDroit(Node root,Stagiaire stagiaire,ArrayList<StagiaireEtNombreEnfants> listStagiaire) {
			int i =-1;
			if (stagiaire.equals(root.getValue())) {
				for(StagiaireEtNombreEnfants filsDroit :listStagiaire) {
					i=i+1;
					if(((root.getRight())!=null)&&(root.getRight().getValue().equals(filsDroit.getStagiaire()))) {
						
						return i;
					}
				}
			
			}else if(stagiaire.getNom().compareToIgnoreCase(root.getValue().getNom())<0) {
				return indexDroit(root.getLeft(),stagiaire,listStagiaire);
					
			}else if(stagiaire.getNom().compareToIgnoreCase(root.getValue().getNom())>0) {
				return indexDroit(root.getRight(),stagiaire,listStagiaire);
			}
			return -999999999;
				
			
				

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
	
	  @Override
	public String toString() {
		return "ConvertTreeToBinaryFile [tree=" + tree + ", treeToListTrie()=" + treeToListTrie() + "]";
	}



	public BinaryTree fromArrayToTree(List<Stagiaire> stagiaires) {
			BinaryTree result = new BinaryTree((stagiaires.get(0)));
			for (Stagiaire stagiaire : stagiaires) {
				result.getRoot().ajouter(stagiaire);
			}
			return result;
			
		}
}
