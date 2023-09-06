package annuaireIsika.Annuaire;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BinaryTree implements Serializable{
	
	private Stagiaire stagiaireFile;

	@Override
	public String toString() {
		return "BinaryTree [root=" + root + "]";
	}

	private Node root;

	public BinaryTree(Stagiaire root) {
		super();
		this.root = new Node(root);
		stagiaireFile = new Stagiaire("", "", "", "", 0);
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	// methodes

	public BinaryTree fromArrayToTree(List<Stagiaire> stagiaires) {
		BinaryTree result = new BinaryTree((stagiaires.get(0)));
		for (Stagiaire stagiaire : stagiaires) {
			result.getRoot().ajouter(stagiaire);
		}
		return result;

	}

	// faire un liste

	public ObservableList<Stagiaire> makeAList() {
		ObservableList<Stagiaire> observableList = FXCollections.observableArrayList();
		makeAListRecursive(this.root, observableList);
		return observableList;
	}

	private void makeAListRecursive(Node node, ObservableList<Stagiaire> observableList) {
		if (node == null) {
			return; // Arrêt de la récursion si le nœud est nul
		}

		makeAListRecursive(node.getLeft(), observableList); // Parcours du sous-arbre gauche (G)
		observableList.add(node.getValue()); // Ajout du nœud courant à l'ObservableList
		makeAListRecursive(node.getRight(), observableList); // Parcours du sous-arbre droit (D)
	}

	public List<Stagiaire> loadFromTheFile() {
		// Crée une liste vide pour stocker les objets Stagiaire
		List<Stagiaire> stagiaires = new ArrayList<>();

		// Définit le chemin du fichier à lire
		URL filePath = getClass().getClassLoader().getResource("STAGIAIRES.DON");
		 System.out.println(filePath);
		// Démarre un bloc try-catch pour gérer les exceptions potentielles lors de la
		// lecture du fichier
		try  {
			BufferedReader reader = new BufferedReader(new FileReader(filePath.getFile()));
			// Déclare une variable pour stocker chaque ligne lue du fichier
			String line;
			System.out.println("coucou");
			// Initialise des variables pour stocker les attributs d'un stagiaire
			String nom = null;
			String prenom = null;
			String departement = null;
			String classe = null;
			int anneeRentree = 0;

			System.out.println("fichier don trouvé");
			
			// Démarre une boucle while pour lire chaque ligne du fichier
			while ((line = reader.readLine()) != null) {
				// Vérifie si la ligne actuelle est égale à "*". Si c'est le cas, cela signifie
				// qu'un nouveau stagiaire commence.
				if (line.equals("*")) {
					// Vérifie si toutes les informations nécessaires pour créer un stagiaire sont
					// disponibles.
					if (nom != null && prenom != null && departement != null && classe != null && anneeRentree != 0) {
						// Crée un nouvel objet Stagiaire avec les informations lues et l'ajoute à la
						// liste.
						Stagiaire stagiaire = new Stagiaire(nom, prenom, departement, classe, anneeRentree);
						stagiaires.add(stagiaire);
					}
					// Réinitialise les variables pour les attributs du prochain stagiaire
					nom = null;
					prenom = null;
					departement = null;
					classe = null;
					anneeRentree = 0;
				} else {
					// Sinon, la ligne contient un attribut du stagiaire en cours de lecture
					if (nom == null) {
						nom = line;
					} else if (prenom == null) {
						prenom = line;
					} else if (departement == null) {
						// Si departement est 0, cela signifie que nous n'avons pas encore lu cette
						// information
						departement = line;
					} else if (classe == null) {
						classe = line;
					} else {
						// Si anneeRentree est 0, cela signifie que nous n'avons pas encore lu cette
						// information
						anneeRentree = Integer.parseInt(line);
					}
				}
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("oups");
			e.printStackTrace();
			// Gère toute exception qui pourrait se produire lors de la lecture du fichier
			// (cette partie doit être remplie en fonction de votre gestion d'erreurs).
		}

		// Retourne la liste des stagiaires après avoir parcouru tout le fichier
		return stagiaires;
	}

	
	
	//test
	
	public void treeToFile(){
		BinaryTree arbre = fromArrayToTree(stagiaireFile.loadFromTheFile());
		int i =0;
		// TODO Auto-generated method stub
		try (FileOutputStream fos = new FileOutputStream("example.bin");
			     ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			for (Stagiaire stagiaire : stagiaireFile.loadFromTheFile()) {
		    oos.writeObject(stagiaire);
		    oos.write(-1);
		    oos.write(-1);
		    oos.writeBytes("*");
		    
		    }
			 
			} catch (IOException e) {
			    e.printStackTrace();}
		//maintenant je marque les index
		for (Stagiaire stagiaire : stagiaireFile.loadFromTheFile()) {
			writeInFile(stagiaire,i);
			i=i+1;
		}
		
			}
		
	
	
		public void writeInFile(Stagiaire stagiaire,int i) {
			// File name to open for reading and writing
	        String fileName = "example.bin";

	        try {
	            // Open the file in "read-write" mode ( remplace l'endroit viser )
	            RandomAccessFile file = new RandomAccessFile(fileName, "rw");
	            
	            //seek end file
	            file.seek(SeekToCharacter()-4);
	    
	            // Write data to the file
	            //String dataToWrite = "This is new data to append.";
	            //file.writeBytes(dataToWrite);

	            file.write(indexGauche(stagiaire,fromArrayToTree(stagiaireFile.loadFromTheFile()).getRoot(),stagiaireFile.loadFromTheFile()));
	            
	            // Close the file
	            file.close();


	        } catch (IOException e) {
	            e.printStackTrace();
	        }

		
	}
		
		public int SeekToCharacter() {
		    
			String fileName = "example.bin";
	        char targetChar = '*';
	        
	        try {
	            FileInputStream fileInputStream = new FileInputStream(fileName);
	            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

	            int position = 0;
	            int character;
	            
	            while ((character = bufferedInputStream.read()) != -1) {
	                char currentChar = (char) character;
	                position++;
	                
	                if (currentChar == targetChar) {
	                    
	                    return position;
	                }
	            }

	            bufferedInputStream.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return 0;
	    }
		    

	
	
public int indexGauche(Stagiaire stagiaire, Node root, List <Stagiaire> stagiaires) {
	//GND
	if (root.getLeft()!=null) { // je peux aller a gauche ?
		if (indexGauche(stagiaire,root.getLeft(),stagiaires)!=0) {
			return indexGauche(stagiaire,root.getLeft(),stagiaires);
		}
	}
	
	//je check la node
	if (root.getValue().compareTo(stagiaire)==0) {
		//on cherche root.getLeft().getValue qui est un stagiaire dans la liste maintenant et on renvoie l'indice
		return stagiaires.indexOf(stagiaire);		
	}
	else if (root.getRight()!=null) {// je peux aller a droite ?
		if (indexGauche(stagiaire,root.getRight(),stagiaires)!=0) {
			return indexGauche(stagiaire,root.getRight(),stagiaires);
		}
	}
	
	return 0;
	
}

public void read() {
    String fileName = "example.bin";

    try {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

        int bytesRead;
        byte[] buffer = new byte[1024]; // You can adjust the buffer size as needed

        while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
            // Process the read data here
            // 'buffer' contains 'bytesRead' bytes of data from the file
            // You can convert these bytes into other data types or manipulate them as needed
        }

        bufferedInputStream.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}

