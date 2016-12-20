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
 * Created by antz on 19/12/2016.
 */
public class Veokid {
    int veokidkokku;
    int kandevoimekokku;
    int alusekohtikokku;
    int maxtellimusikokku;
    List<Veok> VeokiteKogu;

    public Veokid () {
        this.veokidkokku = 0;
        this.kandevoimekokku = 0;
        this.alusekohtikokku = 0;
        this.maxtellimusikokku = 0;
    }

    public void VeokidKokku() {
        Database a = new Database();
        VeokiteKogu = a.VeokiteKogu();
        System.out.println(VeokiteKogu);
        a.sulgeYhendus();
    }
    public void VeokidImport(int mitu) throws FileNotFoundException {
        Database a = new Database();
        Scanner s = null;
        try {
            s = new Scanner(new File("C:\\Users\\antz\\Kood\\javaTranspordiplaneerimine\\src\\b.txt")).useDelimiter("\\s+");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 1; i <= mitu; i++) {
            Veok newVeokid = new Veok(0, 0, i);
            if (s.hasNextInt()) {
                int kandevoime = s.nextInt();
                newVeokid.setKandevoime(kandevoime);
                kandevoimekokku = kandevoimekokku + kandevoime;
                int alusekohti = s.nextInt();
                newVeokid.setAlusekohti(alusekohti);
                alusekohtikokku = alusekohtikokku + alusekohti;
                int maxtellimusi = s.nextInt();
                newVeokid.setMaxtellimusi(maxtellimusi);
                maxtellimusikokku = maxtellimusikokku + maxtellimusi;
                newVeokid.vabaalusekohti = alusekohti;

                a.salvestaVeok(kandevoime, alusekohti, maxtellimusi);
            }
            veokidkokku = veokidkokku + 1;

        }
        System.out.println("Lisatud veokid");
        VeokiteKogu = a.VeokiteKogu();
        a.sulgeYhendus();
    }
    public void ImportPopup () {
        final int[] mitu = {0};
        Stage stage = new Stage();
        GridPane pane = new GridPane();
        Scene stseen = new Scene(pane, 300, 100);
        TextField sisesta = new TextField();
        Label pealkiri = new Label("Mitu veokit?");
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
                VeokidImport(mitu[0]);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    public int getVabadAlused(int i) {
        return VeokiteKogu.get(i).vabaalusekohti;
    }
    public int VabadAlusedkokku () {
        int sum = 0;
        for (int i = 0; i < veokidkokku; i++) {
            sum = getVabadAlused(i) + sum;

        }
        return sum;
    }
    public void setVabadAlused (int i, int j) {
        VeokiteKogu.get(i).setVabaalusekohti(j);
    }
    public void changeVabadAlused (int i, int j){
        int praegu = VeokiteKogu.get(i).vabaalusekohti;
        int parast = praegu - j;
        setVabadAlused(i, parast);
    }
    public void tyhjendaVeokid (){
        for (int i = 0; i < veokidkokku; i++) {
            setVabadAlused(i, VeokiteKogu.get(i).alusekohti);
            System.out.println(VeokiteKogu.get(i));
        }
    }
}
