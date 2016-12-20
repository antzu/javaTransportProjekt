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
        //Name column
        TableColumn<Tellimus, String> kaalColumn = new TableColumn<>("Kaal");
        kaalColumn.setMinWidth(200);
        kaalColumn.setCellValueFactory(new PropertyValueFactory<>("kaal"));

        //Price column
        TableColumn<Tellimus, Double> aluseidColumn = new TableColumn<>("Aluseid");
        aluseidColumn.setMinWidth(100);
        aluseidColumn.setCellValueFactory(new PropertyValueFactory<>("alused"));

        //Quantity column
        TableColumn<Tellimus, String> nrColumn = new TableColumn<>("Nr");
        nrColumn.setMinWidth(100);
        nrColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        table = new TableView<>();
        table.setItems(getTellimus());
        table.getColumns().addAll(nrColumn, aluseidColumn, kaalColumn);

    }
    public ObservableList<Tellimus> getTellimus(){
        ObservableList<Tellimus> tellimused = FXCollections.observableArrayList();
        Tellimused tellimused1 = new Tellimused();
        tellimused1.TellimusedKokku();
        for (int i = 0; i < tellimused1.TellimusteKogu.size(); i++) {
            tellimused.add(new Tellimus(tellimused1.getKaal(i), tellimused1.getAlused(i), tellimused1.getNumber(i)));
        }

        return tellimused;
    }
    public void updateTable (){
        table.setItems(getTellimus());
    }
}
