import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by antz on 19/12/2016.
 */
public class Kasutajaliides {
    private static Stage stage = new Stage();
    private static Scene sc;
    static BorderPane bpane;

    public Kasutajaliides () throws Exception {
        try {
            setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void setup () throws Exception {
        bpane = new BorderPane();
        sc = new Scene(bpane, 600, 600);
        stage.setScene(sc);
        stage.setTitle("Transpordiplaneerija");
        stage.show();

        //Vasak paneel:
        VBox transactions = new VBox();
        transactions.setPadding(new Insets(5, 10, 5, 5));
        transactions.setSpacing(5);
        Button impordi = new Button("IMPORT Tellimused");
        Button prindi = new Button("Prindi");
        Button impordiveokid = new Button("IMPORT Veokid");
        Button koormad = new Button("GENEREERI Koormad");

        transactions.getChildren().add(impordi);
        transactions.getChildren().add(prindi);
        transactions.getChildren().add(impordiveokid);
        transactions.getChildren().add(koormad);
        bpane.setLeft(transactions);

        //Keskmine tabeli paneel ülemine osa:
        Tabel tabel = new Tabel();
        VBox tabeliala = new VBox();
        tabeliala.getChildren().addAll(tabel.table);
        bpane.setCenter(tabeliala);

        //Keskmine tabeli paneel alumine osa (menüü):
        //
        TextField nrInput = new TextField();
        nrInput.setPromptText("Nr");
        nrInput.setMinWidth(100);
        //
        TextField alusedInput = new TextField();
        alusedInput.setPromptText("Alused");
        //
        TextField kaalInput = new TextField();
        kaalInput.setPromptText("Kaal");
        //Buttons
        Button addButton = new Button("Lisa");
        Button deleteButton = new Button("Kustuta");
        //Menu
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10,10,10,10));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(nrInput, alusedInput, kaalInput, addButton, deleteButton);
        //
        bpane.setBottom(hbox);

        //Nupoude käsklused:
        impordi.setOnAction(event -> {
                Tellimused tellimused = new Tellimused();
                tellimused.ImportPopup();
        });
        prindi.setOnAction(event -> {
                Tellimused tellimused = new Tellimused();
                tellimused.TellimusedKokku();
                tabel.updateTable();
        });
        impordiveokid.setOnAction(event -> {
            Veokid veokid = new Veokid();
            veokid.ImportPopup();
        });
        koormad.setOnAction(event -> {
            new Koormad();
        });
        addButton.setOnAction(event -> {
            Tellimus tellimus = new Tellimus();
            //Gets user input from textfields
            tellimus.setNumber(Integer.parseInt(nrInput.getText()));
            int n = tellimus.getNumber();
            tellimus.setAlused(Integer.parseInt(alusedInput.getText()));
            int a = tellimus.getAlused();
            tellimus.setKaal(Integer.parseInt(kaalInput.getText()));
            int k = tellimus.getKaal();
            //Saves user input to Database also
            Database db = new Database();
            db.salvestaTellimus(n, k, a, 0);
            db.sulgeYhendus();
            //Updates UI table according to input
            tabel.updateTable();
            nrInput.clear();
            alusedInput.clear();
            kaalInput.clear();
        });
        deleteButton.setOnAction(event -> {
            ObservableList<Tellimus> tellimusSelected, allTellimused;
            allTellimused = tabel.table.getItems();
            tellimusSelected = tabel.table.getSelectionModel().getSelectedItems();
            Database db = new Database();
            for (int i = 0; i < tellimusSelected.size(); i++) {
                db.kustutaTellimus(tellimusSelected.get(i).getNumber());
            }
            db.sulgeYhendus();
            tellimusSelected.forEach(allTellimused::remove);
        });
    }
}

