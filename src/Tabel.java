import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * Created by antz on 19/12/2016.
 */
public class Tabel {

    TableView<Tellimus> table;


    public Tabel () throws Exception {
        try {
            setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setup () throws Exception{
        //Kaal column
        TableColumn<Tellimus, Integer> kaalColumn = new TableColumn<>("Kaal");
        kaalColumn.setMinWidth(200);
        kaalColumn.setCellValueFactory(new PropertyValueFactory<>("kaal"));

        //Alused column
        TableColumn<Tellimus, Integer> aluseidColumn = new TableColumn<>("Aluseid");
        aluseidColumn.setMinWidth(100);
        aluseidColumn.setCellValueFactory(new PropertyValueFactory<>("alused"));

        //Nr column
        TableColumn<Tellimus, Integer> nrColumn = new TableColumn<>("Nr");
        nrColumn.setMinWidth(100);
        nrColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        //Staatus column
        TableColumn<Tellimus, Integer> staatusColumn = new TableColumn<>("Staatus");
        staatusColumn.setMinWidth(100);
        staatusColumn.setCellValueFactory(new PropertyValueFactory<>("staatus"));

        //Koormanr column
        TableColumn<Tellimus, Integer> koormanrColumn = new TableColumn<>("KoormaNr");
        koormanrColumn.setMinWidth(100);
        koormanrColumn.setCellValueFactory(new PropertyValueFactory<>("koormanr"));

        table = new TableView<>();
        table.setItems(getTellimus());
        table.getColumns().addAll(nrColumn, aluseidColumn, kaalColumn, staatusColumn, koormanrColumn);

    }
    public ObservableList<Tellimus> getTellimus(){
        ObservableList<Tellimus> tellimused = FXCollections.observableArrayList();
        Tellimused tellimused1 = new Tellimused();
        tellimused1.TellimusedKokku();
        for (int i = 0; i < tellimused1.TellimusteKogu.size(); i++) {
            tellimused.add(tellimused1.TellimusteKogu.get(i));//(new Tellimus(tellimused1.getKaal(i), tellimused1.getAlused(i), tellimused1.getNumber(i), tellimused1.getStaatus(i)));
        }

        return tellimused;
    }
    public void updateTable (){
        table.setItems(getTellimus());
    }
}
