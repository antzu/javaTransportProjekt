import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by antz on 20/12/2016.
 */
public class Koormad {
    public static ArrayList<Integer> autod;
    public static int[] DATA;
    GetAllSubsetsByStack sets;
    int koormanr = 1;

    public void getKoormanr() {
        Database a = new Database();
        if (a.Koormanr() != 0) {
            koormanr = a.Koormanr();
        } else {
            koormanr = 1;
        }
        a.sulgeYhendus();
    }

    public Koormad() {
        getKoormanr();
        getDATA(); //muudab Tellimustekogu algoritmi jaoks sobilikule kujule
        getAutod(); //Teeb veokitelistist array, mis koosneb vaid alusekohtadest, muidu on autodel rohkem parameetreid
        ImportPopup(); //Küsib mitu koormat tahad teha ning alustab algoritmi

    }

    public void getDATA() {
        Tellimused tellimused = new Tellimused();
        tellimused.TellimusedKokkuSt();
        DATA = new int[tellimused.TellimusteKogu.size()];
        for (int i = 0; i < tellimused.TellimusteKogu.size(); i++) {
            DATA[i] = tellimused.getAlused(i);
        }
    }

    public void getAutod() {
        Veokid veokid = new Veokid();
        veokid.VeokidKokku();
        autod = new ArrayList();
        for (int i = 0; i < veokid.VeokiteKogu.size(); i++) {
            autod.add(veokid.getAlusekohti(i));
        }
    }

    public void getKoormad(int mitu) {
        if (mitu > autod.size()) {
            ImportPopup();
        } else {
            for (int i = 0; i < mitu; i++) {
                getDATA();
                if (autod.get(i) <= sumDATA(DATA)) {
                    sets = new GetAllSubsetsByStack(autod.get(i), DATA); //Teeb DATA baasil tellimustest autole sobiva kombinatsiooni
                    sets.toStacklist(autod.get(i)); //Lisab kombinatsiooni kombinatsioonide arraysse
                    TellimusToKoorem(sets.stackslist.get(0)); //Votab selle sama kombinatsiooni ja teeb koormaks (siin oleks voimalik algoritmi paremaks teha)
                } else {
                    TellimusToKoorem(DATAarray(DATA)); //Kui tellimusi on vähem, kui autol ruumi, siis lisab kõik
                }
                koormanr++;

            }
        }
    }

    public void ImportPopup() {
        final int[] mitu = {0};
        Stage stage = new Stage();
        GridPane pane = new GridPane();
        Scene stseen = new Scene(pane, 300, 100);
        TextField sisesta = new TextField();
        Label pealkiri = new Label("Mitu koormat?");
        Button kinnita = new Button("Kinnita");

        pane.add(pealkiri, 1, 1);
        pane.add(sisesta, 1, 2);
        pane.add(kinnita, 2, 2);

        stage.setScene(stseen);
        stage.show();

        kinnita.setOnAction(event -> {
            mitu[0] = Integer.parseInt(sisesta.getText());
            stage.close();
            getKoormad(mitu[0]);
        });
    }

    public void TellimusToKoorem(ArrayList stack) {
        Tellimused tellimused = new Tellimused();
        tellimused.TellimusedKokkuSt(); //updates data from DB
        Database a = new Database();
        for (int i = 0; i < stack.size(); i++) {
            int j = tellimused.getByAlused((int) stack.get(i));
            tellimused.setStaatus(j, 1);

            a.updateTellimusStaatus(tellimused.getNumber(j), 1);
            a.updateTellimusKoormanr(tellimused.getNumber(j), koormanr);
        }
        a.sulgeYhendus();
    }

    public int sumDATA(int[] DATA) {
        int sum = 0;
        for (int i = 0; i < DATA.length; i++) {
            sum += DATA[i];
        }
        return sum;
    }

    public ArrayList DATAarray(int[] DATA) {
        ArrayList data = new ArrayList();
        for (int i = 0; i < DATA.length; i++) {
            data.add(DATA[i]);
        }
        return data;
    }
}

