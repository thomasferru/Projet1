package annuaireIsika.Annuaire.back;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SharedDataModel {
    private List<Stagiaire> dataList;

    public SharedDataModel() {
        dataList = FXCollections.observableArrayList();
    }

    public ObservableList<Stagiaire> getDataList() {
        return dataList;
    }


}