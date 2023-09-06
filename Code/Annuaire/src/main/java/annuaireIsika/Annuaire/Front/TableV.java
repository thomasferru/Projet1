package annuaireIsika.Annuaire.Front;

import java.util.List;

import annuaireIsika.Annuaire.back.BinaryTree;
import annuaireIsika.Annuaire.back.Stagiaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class TableV extends VBox{
	
	private BinaryTree tree;
	public TableV() {
		super();
		tree = new BinaryTree(new Stagiaire(null, null, null, null, 0));

		TableView<Stagiaire> tableView = new TableView<>();

		// test
		List<Stagiaire> stagiaires = tree.loadFromTheFile();

		Stagiaire rootStagiaire = stagiaires.get(0);

		BinaryTree test = new BinaryTree(rootStagiaire);

		ObservableList<Stagiaire> list = test.makeAList();
		System.out.println(stagiaires);

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

		tableView.setItems(FXCollections.observableArrayList(stagiaires));
		this.getChildren().add(tableView);

	}

}
