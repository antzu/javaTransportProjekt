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
    TableView<Veok> table2;


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

        //Veokid kandevõime
        TableColumn<Veok, Integer> kandeColumn = new TableColumn<>("Kandevoime");
        kandeColumn.setMinWidth(200);
        kandeColumn.setCellValueFactory(new PropertyValueFactory<>("kandevoime"));
        //Veokid alusekohti
        TableColumn<Veok, Integer> aluseColumn = new TableColumn<>("Alusekohti");
        aluseColumn.setMinWidth(200);
        aluseColumn.setCellValueFactory(new PropertyValueFactory<>("alusekohti"));
        //Veokid maxtellimusi
        TableColumn<Veok, Integer> maxtellimusiColumn = new TableColumn<>("Maxtellimusi");
        maxtellimusiColumn.setMinWidth(200);
        maxtellimusiColumn.setCellValueFactory(new PropertyValueFactory<>("maxtellimusi"));

        table2 = new TableView<>();
        table2.setItems(getVeok());
        table2.getColumns().addAll(kandeColumn, aluseColumn, maxtellimusiColumn);
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
    public ObservableList<Veok> getVeok(){
        ObservableList<Veok> veokid = FXCollections.observableArrayList();
        Veokid veokid1 = new Veokid();
        veokid1.VeokidKokku();
        for (int i = 0; i < veokid1.VeokiteKogu.size(); i++) {
            veokid.add(veokid1.VeokiteKogu.get(i));//(new Tellimus(tellimused1.getKaal(i), tellimused1.getAlused(i), tellimused1.getNumber(i), tellimused1.getStaatus(i)));
        }

        return veokid;
    }
    public void updateTable (){
        table.setItems(getTellimus());
        table2.setItems(getVeok());
    }
}
