import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
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

        VBox transactions = new VBox();
        Button impordi = new Button("IMPORT Tellimused");
        Button prindi = new Button("Prindi");
        Button impordiveokid = new Button("IMPORT Veokid");

        transactions.getChildren().add(impordi);
        transactions.getChildren().add(prindi);
        transactions.getChildren().add(impordiveokid);
        bpane.setLeft(transactions);

        Tabel tabel = new Tabel();
        VBox tabeliala = new VBox();
        tabeliala.getChildren().addAll(tabel.table);
        bpane.setCenter(tabeliala);


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
    }
}

