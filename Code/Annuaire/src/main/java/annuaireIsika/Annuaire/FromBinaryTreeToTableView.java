package annuaireIsika.Annuaire;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FromBinaryTreeToTableView extends VBox{
	
	private BinaryTree binaryTree;
	private TableView<Node> maTable;
	
	//public List<Node>NodesList fromBinaryTreeToArray(){
		//ist<Node> NodesList = new ArrayList<>();
		//while () {
			
		//}
		//return ;
		
	//}

	public void PromotionView() {
		
		

		this.maTable = new TableView<Node>();
		maTable.setEditable(true);

		// **********Création de la colonne nom.**********
		// Etape 1 : je créé la colonne.
		TableColumn<Node, String> colonneNom = new TableColumn<Node, String>("Nom");
		// Etape 2 : je donne une largeur à ma colonne.
		colonneNom.setMinWidth(100);
		// Etape 3 : je précise comment remplir la colonne.
		colonneNom.setCellValueFactory(new PropertyValueFactory<Node, String>("nom"));
		// Etape 4 : j'ajoute la colonne au TableView.
		//maTable.getColumns().add(colonneNom);
		//Etape5 : j'ajoute un gestionnaire dévènement pour les cellules de ma colonne.
		colonneNom.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Node, String>>() {
			@Override
			public void handle(CellEditEvent<Node, String> event) {
			//Je récupère l'objet qui correspond à la ligne modifiée
				((Node)maTable.getItems().get((event.getTablePosition().getRow())))
					.getValue().setNom(event.getNewValue());//On récupère la nouvelle valeur dans l'event
			}
		});
	colonneNom.setCellFactory(TextFieldTableCell.forTableColumn()); //On autorise à transformer la case en textfield.
	colonneNom.setEditable(true);//On autorise la modification des colonnes.
	
	maTable.getSelectionModel().getSelectedItem();

		// **********Création de la colonne prénom.**********
		// Etape 1 : je créé la colonne.
		TableColumn<Node, String> colonnePrenom = new TableColumn<Node, String>("Prénom");
		// Etape 2 : je donne une largeur à ma colonne.
		colonnePrenom.setMinWidth(100);
		// Etape 3 : je précise comment remplir la colonne.
		colonnePrenom.setCellValueFactory(new PropertyValueFactory<Node, String>("prenom"));
		// Etape 4 : j'ajoute la colonne au TableView.
		//maTable.getColumns().add(colonnePrenom);

		// **********Création de la colonne departement**********
		// Etape 1 : je créé la colonne.
		TableColumn<Node, Integer> colonneDepartement = new TableColumn<Node, Integer>("Departement");
		// Etape 2 : je donne une largeur à ma colonne.
		colonneDepartement.setMinWidth(50);
		// Etape 3 : je précise comment remplir la colonne.
		colonneDepartement.setCellValueFactory(new PropertyValueFactory<Node, Integer>("departement"));
		// Etape 4 : j'ajoute la colonne au TableView.
		//maTable.getColumns().add(colonneDepartement);
		
		// **********Création de la colonne Promo.**********
		// Etape 1 : je créé la colonne.
		TableColumn<Node, String> colonnePromo = new TableColumn<Node, String>("Promo");
		// Etape 2 : je donne une largeur à ma colonne.
		colonnePromo.setMinWidth(50);
		// Etape 3 : je précise comment remplir la colonne.
		colonnePromo.setCellValueFactory(new PropertyValueFactory<Node, String>("Promo"));
		// Etape 4 : j'ajoute la colonne au TableView.
		//maTable.getColumns().add(colonnePromo);

		// **********Création de la colonne age.**********
		// Etape 1 : je créé la colonne.
		TableColumn<Node, Integer> colonneAge = new TableColumn<Node, Integer>("Age");
		// Etape 2 : je donne une largeur à ma colonne.
		colonneAge.setMinWidth(50);
		// Etape 3 : je précise comment remplir la colonne.
		colonneAge.setCellValueFactory(new PropertyValueFactory<Node, Integer>("age"));
		// Etape 4 : j'ajoute la colonne au TableView.
		//maTable.getColumns().add(colonneAge);

		// *********Mise en place de la liste observable**********
		maTable.setItems(FXCollections.observableList(maPromo.groupe));

		this.getChildren().add(maTable);
		
		ObservableList<Stagiaire> stagiaireList = FXCollections.observableArrayList();
		populateStagiaireList(binaryTree.getRoot(), stagiaireList);

		TableView.setItems(stagiaireList);
		
		//J'ai fini de créer ma Table.
		HBox formulaireAjout = new HBox();
		TextField nomTF = new TextField("nom");
		TextField prenomTF = new TextField("prenom");
		TextField ageTF = new TextField("age");		
		formulaireAjout.getChildren().addAll(nomTF, prenomTF, ageTF);
		this.getChildren().add(formulaireAjout);
		
		Button ajouterNode = new Button("Ajouter");
		this.getChildren().add(ajouterNode);
		ajouterNode.setPrefWidth(260);
		
		//ajouterNode.setOnAction(new EventHandler<ActionEvent>() {			
			//@Override
			//public void handle(ActionEvent event) {
				//Node nouvelleNode = new Node(nomTF.getText()
					//						, prenomTF.getText()
						//					, Integer.valueOf(ageTF.getText()));
				//maTable.getItems().add(nouvelleNode);
				
			//}
		//});	
	}}
