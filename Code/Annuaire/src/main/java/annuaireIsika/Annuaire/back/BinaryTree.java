package annuaireIsika.Annuaire.back;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
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

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BinaryTree implements Serializable {

	private Stagiaire stagiaireFile;
	private Node root;
	
	
	@Override
	public String toString() {
		return "BinaryTree [root=" + root + "]";
	}

	

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
	// a enlever
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

	// utiliser celle de stagiaire plutot
	public List<Stagiaire> loadFromTheFile() {
		// Crée une liste vide pour stocker les objets Stagiaire
		List<Stagiaire> stagiaires = new ArrayList<>();

		// Définit le chemin du fichier à lire
		URL filePath = getClass().getClassLoader().getResource("STAGIAIRES.DON");
		System.out.println(filePath);
		// Démarre un bloc try-catch pour gérer les exceptions potentielles lors de la
		// lecture du fichier
		try {
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

	// test

	public void treeToFile() throws IOException, InterruptedException {

		// TODO creer le fichier binaire
		try (FileOutputStream fos = new FileOutputStream("example.bin")) {
		    // You can leave this block empty, and it will create an empty "example.bin" file
		} catch (IOException e) {
		    e.printStackTrace();
		}
		// TODO mettre le raf en attribut de la classe, je l'initialise dans le
		// constructeur
		
		//fait
		
		
		for (Stagiaire stagiaire : stagiaireFile.loadFromTheFile()) {

			 ajouterArbre(stagiaire);

		
		}

	}
	
	public void ajouterArbre(Stagiaire stagiaireAjout) throws IOException, InterruptedException {
		File file = new File("example.bin");
		//si arbre vide -> si ton fichier binaire est vide 
		//tu te mets au debut du fichier et tu éris noeudAjout (stagiaire, -1,-1)
	
		String fileName = "example.bin";
		// si le fichier est vide
		if (file.length()==0) {
			

	        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
	             DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream)) {

	            // Write binary data to the file
	           
	            
	            dataOutputStream.writeBytes(stagiaireAjout.getNom());
	            dataOutputStream.writeBytes(stagiaireAjout.getPrenom());
	            dataOutputStream.writeBytes(stagiaireAjout.getDepartement());
	            dataOutputStream.writeBytes(stagiaireAjout.getPromotion());
	            dataOutputStream.writeInt(stagiaireAjout.getAnneeFormation());
	            dataOutputStream.writeInt(-1);
	            dataOutputStream.writeInt(-1);
	            dataOutputStream.writeInt(-1);
	          

	            System.out.println("Data has been written to " + fileName);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }else {
			
		
	
		//sinon
			//tu lis le premier noeud tu le stockes dans une variable racine

		Stagiaire first = new Stagiaire(null, null, null, null, 0);
		int totalOctets = 160;
        int octetsPerString = 20;
        int numStrings = totalOctets / octetsPerString;

        
           
            try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
                String nom = readString(raf, octetsPerString);
                String prenom = readString(raf, octetsPerString);
                String departement = readString(raf, octetsPerString);
                String promotion = readString(raf, octetsPerString);

                first.setNom(nom);
                first.setPrenom(prenom);
                first.setDepartement(departement);
                first.setPromotion(promotion);
                
                int anneeFormation = raf.readInt();
                first.setAnneeFormation(anneeFormation);
                
                Thread.sleep(1000);
                raf.seek(raf.length());
                Node firstNode = new Node(first);
                firstNode.ajouterBinaire(stagiaireAjout,raf);
            } catch (IOException e) {
                e.printStackTrace();}
            }
		
		
            }
	
            
            private static String readString(RandomAccessFile raf, int length) throws IOException {
                byte[] buffer = new byte[length];
                int bytesRead = raf.read(buffer);

                if (bytesRead != -1) {
                    return new String(buffer, "UTF-8"); // Adjust encoding if necessary
                } else {
                    return null;
                
            }
	
	}}


