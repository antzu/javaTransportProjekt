import java.sql.*;
import java.util.ArrayList;

/**
 * Created by antz on 19/12/2016.
 */
//https://github.com/KristerV/javaSQLNaide/blob/master/src/Andmebaas.java põhjal
public class Database {
    Connection conn = null;

    public Database() {
        looYhendus();
        looTellimusedTabel();
        looVeokidTabel();
    }
    private void looYhendus() {
        try {
            Class.forName("org.sqlite.JDBC");                          // Lae draiver sqlite.jar failist
            conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\antz\\Kood\\javaTransportProjekt/test.db"); // loo ühendus andmebaasi failiga
        } catch ( Exception e ) {                                      // püüa kinni võimalikud errorid
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        System.out.println("Opened database successfully");
    }

    public void looTellimusedTabel(){
        String sql = "CREATE TABLE IF NOT EXISTS TELLIMUSED (TELLIMUSNR INTEGER, " +
                "KAAL INTEGER, ALUSED INTEGER, STAATUS INTEGER);";
        teostaAndmebaasiMuudatus(sql);
    }
    public void looVeokidTabel(){
        String sql = "CREATE TABLE IF NOT EXISTS VEOKID (KANDEVOIME INTEGER, ALUSEKOHTI INTEGER, MAXTELLIMUSI INTEGER);";
        teostaAndmebaasiMuudatus(sql);
    }

    // Andmebaasi muudatused ei tagasta väärtusi (erinevalt
    // päringutest) ja on lihtne eraldi meetodi tuua.
    private void teostaAndmebaasiMuudatus(String sql) {
        try {
            // Statement objekt on vajalik, et SQL_Login käsku käivitada
            Statement stat = conn.createStatement();
            stat.executeUpdate(sql);
            stat.close(); // Statement tuleb samuti kinni panna nagu ka Connection.
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void kustutaTellimus (int n) {
            //Statement stat = conn.createStatement();
            String sql = "DELETE FROM TELLIMUSED WHERE TELLIMUSNR like ('"+n+"')";
            //stat.executeUpdate(sql);
            //stat.close();
            teostaAndmebaasiMuudatus(sql);
    }

    public void salvestaTellimus (int n, int k, int a, int s){
        String sql = "INSERT INTO TELLIMUSED (TELLIMUSNR, KAAL, ALUSED, STAATUS) VALUES ('"+n+"', '"+k+"', '"+a+"', '"+s+"')";
        teostaAndmebaasiMuudatus(sql);
    }
    public void salvestaVeok (int k, int a, int m){
        String sql = "INSERT INTO VEOKID (KANDEVOIME, ALUSEKOHTI, MAXTELLIMUSI) VALUES ('"+k+"', '"+a+"', '"+m+"')";
        teostaAndmebaasiMuudatus(sql);
    }
    public void updateTellimusNr(int n, int k){
        String sql = "UPDATE TELLIMUSED SET TELLIMUSNR=('"+k+"') WHERE TELLIMUSNR = ('"+n+"')";
        teostaAndmebaasiMuudatus(sql);
    }
    public void updateTellimusKaal(int n, int k){
        String sql = "UPDATE TELLIMUSED SET KAAL=('"+k+"') WHERE TELLIMUSNR = ('"+n+"')";
        teostaAndmebaasiMuudatus(sql);
    }
    public void updateTellimusAlused(int n, int k){
        String sql = "UPDATE TELLIMUSED SET ALUSED=('"+k+"') WHERE TELLIMUSNR = ('"+n+"')";
        teostaAndmebaasiMuudatus(sql);
    }
    public void updateTellimusStaatus(int n, int k){
        String sql = "UPDATE TELLIMUSED SET STAATUS=('"+k+"') WHERE TELLIMUSNR = ('"+n+"')";
        teostaAndmebaasiMuudatus(sql);
    }


    public void sulgeYhendus() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connection closed!");
    }

    public ArrayList<Tellimus> TellimusteKogu (){
        ArrayList<Tellimus> TellimusteKogu = new ArrayList<>();
        try {
            Statement stat = conn.createStatement();
            String sql = "SELECT * FROM TELLIMUSED WHERE STAATUS = '0'";
            ResultSet rs = stat.executeQuery(sql);

            while (rs.next()) {
                TellimusteKogu.add(new Tellimus(rs.getInt("KAAL"), rs.getInt("ALUSED"), rs.getInt("TELLIMUSNR"), rs.getInt("STAATUS")));
            }

            rs.close();
            stat.close();

            return TellimusteKogu;
        } catch (SQLException e){
            e.printStackTrace();
            System.exit(0);
        }
        return TellimusteKogu;
    } //https://github.com/luksti/ActionBoard/blob/master/src/Andmebaas.java
    public ArrayList<Veok> VeokiteKogu (){
        ArrayList<Veok> VeokiteKogu = new ArrayList<>();
        try {
            Statement stat = conn.createStatement();
            String sql = "SELECT * FROM VEOKID";
            ResultSet rs = stat.executeQuery(sql);

            while (rs.next()) {
                VeokiteKogu.add(new Veok(rs.getInt("KANDEVOIME"), rs.getInt("ALUSEKOHTI"), rs.getInt("MAXTELLIMUSI")));
            }

            rs.close();
            stat.close();

            return VeokiteKogu;
        } catch (SQLException e){
            e.printStackTrace();
            System.exit(0);
        }
        return VeokiteKogu;
    } //https://github.com/luksti/ActionBoard/blob/master/src/Andmebaas.java
}

