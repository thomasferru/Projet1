package annuaireIsika.Annuaire;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TestTv {
	public TestTv() {
		super(); 
		TableView<Stagiaire> tableView = new TableView<>();
		
		// définir les colonnes
			
		TableColumn<Stagiaire, String> nameColumn = new TableColumn<>("Nom");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
		
		TableColumn<Stagiaire, String> prenomColumn = new TableColumn<>("Prénom");
		prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		
		TableColumn<Stagiaire, Integer> depColumn = new TableColumn<>("Département");
		depColumn.setCellValueFactory(new PropertyValueFactory<>("departement"));

		TableColumn<Stagiaire, String> promoColumn = new TableColumn<>("Pomotion");
		promoColumn.setCellValueFactory(new PropertyValueFactory<>("promotion"));
		
		TableColumn<Stagiaire, Integer> anneEntreeColumn = new TableColumn<>("Année d'entrée");
		anneEntreeColumn.setCellValueFactory(new PropertyValueFactory<>("anneeFormation"));
		
		
		 tableView.getColumns().addAll(nameColumn, prenomColumn, depColumn, promoColumn, anneEntreeColumn);
		 ObservableList<Stagiaire> ObsListSatgiaires = makeAList();
		 tableView.setItems(ObsListSatgiaires);
	}
	
	// faire un liste 
	
		public ObservableList<Stagiaire> makeAList() {
		    ObservableList<Stagiaire> observableList = FXCollections.observableArrayList();
		    makeAListRecursive(this, observableList);
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
	
	
	
	
	


}
