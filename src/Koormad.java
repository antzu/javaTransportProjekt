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
    public static int [] DATA;
    GetAllSubsetsByStack sets;

    public Koormad (){
        getDATA();
        getAutod();
        System.out.println(Arrays.toString(DATA));
        System.out.println(autod);
        ImportPopup();

    }
    public void getDATA (){
        Tellimused tellimused = new Tellimused();
        tellimused.TellimusedKokku();
        DATA = new int[tellimused.TellimusteKogu.size()];
        for (int i = 0; i < tellimused.TellimusteKogu.size(); i++) {
            DATA [i] = tellimused.getAlused(i);
        }
    }
    public void getAutod (){
        Veokid veokid = new Veokid();
        veokid.VeokidKokku();
        autod = new ArrayList();
        for (int i = 0; i < veokid.VeokiteKogu.size(); i++) {
            autod.add(veokid.getAlusekohti(i));
        }
    }
    public void getKoormad (int mitu){
        for (int i = 0; i < mitu; i++) {
            getDATA();
            sets = new GetAllSubsetsByStack(autod.get(i), DATA);
            sets.toStacklist(autod.get(i));
            TellimusToKoorem(sets.stackslist.get(0));
        }

    }

    public void ImportPopup () {
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
    public void TellimusToKoorem (ArrayList stack){
        Tellimused tellimused = new Tellimused();
        tellimused.TellimusedKokku();
        Database a = new Database();
        for (int i = 0; i < stack.size(); i++) {
            int j = tellimused.getByAlused((int)stack.get(i));
            tellimused.setStaatus(j, 1);
            a.updateTellimusStaatus(tellimused.getNumber(j), 1);
        }
        a.sulgeYhendus();
    }
}
