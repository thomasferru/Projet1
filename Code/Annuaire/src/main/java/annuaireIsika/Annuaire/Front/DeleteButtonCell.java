package annuaireIsika.Annuaire.Front;

import annuaireIsika.Annuaire.back.Stagiaire;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DeleteButtonCell extends TableCell<Stagiaire, Void> {
	private final Button deleteButton;

	public DeleteButtonCell() {

		this.deleteButton = new Button();
		deleteButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
		Image padlockImage = new Image("file:src/main/resources/Images/Delete.png"); // Remplacez par le chemin correct
																						// de votre image
		ImageView imageView = new ImageView(padlockImage);
		imageView.setFitWidth(16);
		imageView.setFitHeight(16);
		deleteButton.setGraphic(imageView);
		deleteButton.setOpacity(1.0);

		deleteButton.setOnMousePressed(event -> {

			deleteButton.setOpacity(0.5);
		});

		deleteButton.setOnMouseReleased(event -> {

			deleteButton.setOpacity(1.0);
		});

		deleteButton.setOnAction(event -> {
			System.out.println("test");
		});
	}

	// je ne sais pas a quoi ca sert
	@Override
	protected void updateItem(Void item, boolean empty) {
		super.updateItem(item, empty);
		if (empty) {
			setGraphic(null);
		} else {
			setGraphic(deleteButton);
		}
	}
}
