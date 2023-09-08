package annuaireIsika.Annuaire.Front;

import java.io.IOException;
import java.util.List;

import annuaireIsika.Annuaire.back.BinToList;
import annuaireIsika.Annuaire.back.BinaryTree;
import annuaireIsika.Annuaire.back.Stagiaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class TableV extends VBox {
	private boolean connect;
	private int nameCellWidth = 150;
	private int promoCellWidth = 75;
	private int intCellWidth = 50;

	private BinaryTree tree;

	public TableV(boolean connect) throws IOException {
		super();
		this.connect = connect;
		tree = new BinaryTree(new Stagiaire(null, null, null, null, 0));

		TableView<Stagiaire> tableView = new TableView<>();

		// test
		

		BinToList test = new BinToList(); 
		List<Stagiaire> testt = test.binToList();

		
		BinToList listFromBinFile = new BinToList();
		Stagiaire aaa = new Stagiaire("bbbb", "aa",  "aa",  "aa", 0);
		aaa.setStagiaireTailleOctets();
		listFromBinFile.ajouterUnStagiaireAuFichier(aaa);

//		listFromBinFile.ajouterUnStagiaireAuFichier("aaa");

		if (connect == true) {
			// colume bouton

			TableColumn<Stagiaire, Void> editButtonColumn = new TableColumn<>("");
			editButtonColumn.setCellFactory(param -> new EditButtonCell());
			editButtonColumn.setPrefWidth(50);

			TableColumn<Stagiaire, Void> deleteButtonColumn = new TableColumn<>("");
			deleteButtonColumn.setCellFactory(param -> new DeleteButtonCell());
			deleteButtonColumn.setPrefWidth(50);

			//

			TableColumn<Stagiaire, String> nameColumn = new TableColumn<>("Nom");
			nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
			nameColumn.setPrefWidth(nameCellWidth);

			TableColumn<Stagiaire, String> prenomColumn = new TableColumn<>("Prénom");
			prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
			prenomColumn.setPrefWidth(nameCellWidth);

			TableColumn<Stagiaire, String> depColumn = new TableColumn<>("Département"); // a changer
			depColumn.setCellValueFactory(new PropertyValueFactory<>("departement"));
			depColumn.setPrefWidth(intCellWidth);

			TableColumn<Stagiaire, String> promoColumn = new TableColumn<>("Pomotion");
			promoColumn.setCellValueFactory(new PropertyValueFactory<>("promotion"));
			promoColumn.setPrefWidth(promoCellWidth);

			TableColumn<Stagiaire, Integer> anneEntreeColumn = new TableColumn<>("Année d'entrée");
			anneEntreeColumn.setCellValueFactory(new PropertyValueFactory<>("anneeFormation"));
			anneEntreeColumn.setPrefWidth(intCellWidth);

			tableView.getColumns().add(editButtonColumn);
			tableView.getColumns().add(deleteButtonColumn);
			tableView.getColumns().add(nameColumn);
			tableView.getColumns().add(prenomColumn);
			tableView.getColumns().add(depColumn);
			tableView.getColumns().add(promoColumn);
			tableView.getColumns().add(anneEntreeColumn);

			tableView.setItems(FXCollections.observableArrayList(testt));
			this.getChildren().add(tableView);

		} else {

			TableColumn<Stagiaire, String> nameColumn = new TableColumn<>("Nom");
			nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
			nameColumn.setPrefWidth(nameCellWidth);

			TableColumn<Stagiaire, String> prenomColumn = new TableColumn<>("Prénom");
			prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
			prenomColumn.setPrefWidth(nameCellWidth);

			TableColumn<Stagiaire, String> depColumn = new TableColumn<>("Département"); // a changer
			depColumn.setCellValueFactory(new PropertyValueFactory<>("departement"));
			depColumn.setPrefWidth(intCellWidth);

			TableColumn<Stagiaire, String> promoColumn = new TableColumn<>("Pomotion");
			promoColumn.setCellValueFactory(new PropertyValueFactory<>("promotion"));
			promoColumn.setPrefWidth(promoCellWidth);

			TableColumn<Stagiaire, Integer> anneEntreeColumn = new TableColumn<>("Année d'entrée");
			anneEntreeColumn.setCellValueFactory(new PropertyValueFactory<>("anneeFormation"));
			anneEntreeColumn.setPrefWidth(intCellWidth);

			tableView.getColumns().add(nameColumn);
			tableView.getColumns().add(prenomColumn);
			tableView.getColumns().add(depColumn);
			tableView.getColumns().add(promoColumn);
			tableView.getColumns().add(anneEntreeColumn);
			
			tableView.setItems(FXCollections.observableArrayList(testt));
			this.getChildren().add(tableView);
		}
	}


	public List<Stagiaire> getStagiaires() {
		// Obtenez la TableView à partir de la VBox
		TableView<Stagiaire> tableView = (TableView<Stagiaire>) this.getChildren().get(0);

		// Obtenez la liste d'objets Stagiaire de la TableView
		List<Stagiaire> stagiaires = tableView.getItems();

		return stagiaires;
	}

	// pdf
	public TableView<Stagiaire> getTableView() {
		// Obtenez la TableView à partir de cette classe (TableV)
		return (TableView<Stagiaire>) this.getChildren().get(0);
	}

}