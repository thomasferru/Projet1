package annuaireIsika.Annuaire.Front.BorderPane;

import java.io.IOException;
import java.util.List;

import annuaireIsika.Annuaire.back.BinToList;
import annuaireIsika.Annuaire.back.Stagiaire;
import javafx.scene.layout.BorderPane;

public class MainBorderPane extends BorderPane {

	private boolean connect = false;
	private List<Stagiaire> list;

	public MainBorderPane(boolean connect) throws IOException {
		super();
		this.connect = connect;
		BinToList test = new BinToList();
		list = test.binToList();

		LeftPart leftPart = new LeftPart(this);
		RightPart rightPart = new RightPart(this, this.connect);
		CenterPart centerPart = new CenterPart(this, this.connect, list);

		this.setLeft(leftPart);
		this.setRight(rightPart);
		this.setCenter(centerPart);

	}

	public boolean isConnect() {
		return connect;
	}

	public void setConnect(boolean connect) {
		this.connect = connect;
	}

}
