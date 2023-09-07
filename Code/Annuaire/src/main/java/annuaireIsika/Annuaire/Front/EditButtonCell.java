package annuaireIsika.Annuaire.Front;

import annuaireIsika.Annuaire.back.Stagiaire;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EditButtonCell extends TableCell<Stagiaire, Void> {
	private final Button editButton;

	public EditButtonCell() {

		this.editButton = new Button();
		editButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
		Image padlockImage = new Image("file:src/main/resources/Images/Edit.png"); // Remplacez par le chemin correct
																						// de votre image
		ImageView imageView = new ImageView(padlockImage);
		imageView.setFitWidth(16);
		imageView.setFitHeight(16);
		editButton.setGraphic(imageView);
		editButton.setOpacity(1.0);

		editButton.setOnMousePressed(event -> {

			editButton.setOpacity(0.5);
		});

		editButton.setOnMouseReleased(event -> {

			editButton.setOpacity(1.0);
		});

		editButton.setOnAction(event -> {
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
			setGraphic(editButton);
		}
	}
}
