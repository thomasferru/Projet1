package annuaireIsika.Annuaire.Front;

import java.io.IOException;
import java.util.EventListener;
import java.util.List;

import annuaireIsika.Annuaire.back.BinaryTree;
import annuaireIsika.Annuaire.back.Stagiaire;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.util.converter.IntegerStringConverter;

public class TableV extends VBox {
	private boolean connect;
	private int nameCellWidth = 150;
	private int promoCellWidth = 75;
	private int intCellWidth = 50;

	public TableColumn<Stagiaire, String> prenomColumn;
	public TableView<Stagiaire> tableView;
	public String  newPrenom ="";

	private BinaryTree tree;

	public TableV(boolean connect, List<Stagiaire> list) throws IOException {
		super();
		this.connect = connect;
		tree = new BinaryTree(new Stagiaire(null, null, null, null, 0));


		// test

//		BinToList test = new BinToList();
//		List<Stagiaire> testt = test.binToList();

//		listFromBinFile.ajouterUnStagiaireAuFichier("aaa");

		if (connect == true) {
			// colume bouton
			tableView = new TableView<>();
			tableView.setEditable(true);

			TableColumn<Stagiaire, Void> editButtonColumn = new TableColumn<>("");
			editButtonColumn.setCellFactory(param -> new EditButtonCell(this));
			editButtonColumn.setPrefWidth(50);


			TableColumn<Stagiaire, Void> deleteButtonColumn = new TableColumn<>("");
			deleteButtonColumn.setCellFactory(param -> new DeleteButtonCell());
			deleteButtonColumn.setPrefWidth(50);

			//

			TableColumn<Stagiaire, String> nameColumn = new TableColumn<>("Nom");
			nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
			nameColumn.setPrefWidth(nameCellWidth);
//			nameColumn.setOnEditStart(new EventHandler<TableColumn.CellEditEvent<Stagiaire, String>>() {
//				@Override
//				public void handle(CellEditEvent<Stagiaire, String> event) {
//					((Stagiaire) tableView.getItems().get(event.getTablePosition().getRow()))
//							.setNom(event.getNewValue());
//				}
//			});
			nameColumn.setEditable(false);
			nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

			prenomColumn = new TableColumn<>("Prénom");
			prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
			prenomColumn.setPrefWidth(nameCellWidth);
			prenomColumn.setEditable(true);
			prenomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
			prenomColumn.setOnEditCommit(event -> {
				System.out.println(event.getNewValue());
				newPrenom = event.getNewValue();
			});

			TableColumn<Stagiaire, String> depColumn = new TableColumn<>("Département"); // a changer
			depColumn.setCellValueFactory(new PropertyValueFactory<>("departement"));
			depColumn.setPrefWidth(intCellWidth);
			depColumn.setEditable(true);
			depColumn.setCellFactory(TextFieldTableCell.forTableColumn());

			TableColumn<Stagiaire, String> promoColumn = new TableColumn<>("Pomotion");
			promoColumn.setCellValueFactory(new PropertyValueFactory<>("promotion"));
			promoColumn.setPrefWidth(promoCellWidth);
			promoColumn.setEditable(true);
			promoColumn.setCellFactory(TextFieldTableCell.forTableColumn());

			TableColumn<Stagiaire, Integer> anneEntreeColumn = new TableColumn<>("Année d'entrée");
			anneEntreeColumn.setCellValueFactory(new PropertyValueFactory<>("anneeFormation"));
			anneEntreeColumn.setPrefWidth(intCellWidth);
			anneEntreeColumn.setEditable(true);
			anneEntreeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));


			tableView.getColumns().add(editButtonColumn);
			tableView.getColumns().add(deleteButtonColumn);
			tableView.getColumns().add(nameColumn);
			tableView.getColumns().add(prenomColumn);
			tableView.getColumns().add(depColumn);
			tableView.getColumns().add(promoColumn);
			tableView.getColumns().add(anneEntreeColumn);

			tableView.setItems(FXCollections.observableArrayList(list));
			this.getChildren().add(tableView);


		} else {
			tableView = new TableView<>();

			TableColumn<Stagiaire, String> nameColumn = new TableColumn<>("Nom");
			nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
			nameColumn.setPrefWidth(nameCellWidth);


//			nameColumn.setOnEditCommit(new EventHandler<CellEditEvent<Stagiaire, String>>() {
//				@Override
//				public void handle(CellEditEvent<Stagiaire, String> event) {
//					Stagiaire stagiaire = event.getRowValue();
//					stagiaire.setNom(event.getNewValue());
//				}
//			});



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

			tableView.setItems(FXCollections.observableArrayList(list));
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