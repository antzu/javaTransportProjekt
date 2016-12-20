import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by antz on 27/11/2016.
 */
public class Tellimused {
    int alusedkokku;
    int kaalkokku;
    int tellimusikokku;
    List<Tellimus> TellimusteKogu;

    public Tellimused() {
        this.alusedkokku = 0;
        this.kaalkokku = 0;
        this.tellimusikokku = 0;
    }
    public int getKaal (int i) {
        return TellimusteKogu.get(i).getKaal();
    }
    public int getAlused (int i) {
        return TellimusteKogu.get(i).getAlused();
    }
    public int getNumber (int i) {
        return TellimusteKogu.get(i).getNumber();
    }
    public int getStaatus (int i) {
        return TellimusteKogu.get(i).getStaatus();
    }
    public void setStaatus (int i, int j){
        TellimusteKogu.get(i).setStaatus(j);
    }
    public int getByAlused (int i){
        for (int j = 0; j < TellimusteKogu.size(); j++) {
            if (getAlused(j) == i){
                return j;
            }
        }
        return 9999;
    }
    public void TellimusedKokku() {
        Database a = new Database();
        TellimusteKogu = a.TellimusteKogu();
        System.out.println(TellimusteKogu);
        a.sulgeYhendus();
    }
    public void TellimusedImport(int mitu) throws FileNotFoundException {
        Database a = new Database();
        Scanner s = null;
        try {
            s = new Scanner(new File("C:\\Users\\antz\\Kood\\javaTranspordiplaneerimine\\src\\a.txt")).useDelimiter("\\s+");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 1; i <= mitu; i++) {

            Tellimus newTellimused = new Tellimus(0, 0, 0, 0);
            if (s.hasNextInt()) {
                int number = s.nextInt();
                newTellimused.setNumber(number);
                tellimusikokku = tellimusikokku + 1;
                int alused = s.nextInt();
                newTellimused.setAlused(alused);
                alusedkokku = alusedkokku + alused;
                int kaal = s.nextInt();
                newTellimused.setKaal(kaal);
                kaalkokku = kaalkokku + kaal;
                newTellimused.setStaatus(0);
                int staatus = 0;
                a.salvestaTellimus(number, kaal, alused, staatus);

            }
        }
        System.out.println("Lisatud tellimused");
        TellimusteKogu = a.TellimusteKogu();
        a.sulgeYhendus();

    }
    public void ImportPopup () {
        final int[] mitu = {0};
        Stage stage = new Stage();
        GridPane pane = new GridPane();
        Scene stseen = new Scene(pane, 300, 100);
        TextField sisesta = new TextField();
        Label pealkiri = new Label("Mitu tellimust?");
        Button kinnita = new Button("Kinnita");

        pane.add(pealkiri, 1, 1);
        pane.add(sisesta, 1, 2);
        pane.add(kinnita, 2, 2);

        stage.setScene(stseen);
        stage.show();

        kinnita.setOnAction(event -> {
            mitu[0] = Integer.parseInt(sisesta.getText());
            stage.close();
            try {
                TellimusedImport(mitu[0]);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
