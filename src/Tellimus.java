/**
 * Created by antz on 27/11/2016.
 */
public class Tellimus {
    Integer number;
    Integer kaal;
    Integer alused;
    Integer staatus;

    public Tellimus (){
        this.kaal = 0;
        this.alused = 0;
        this.number = 0;
        this.staatus = 0;
    }

    public Tellimus(int kaal, int alused, int number, int staatus) {
        this.kaal = kaal;
        this.alused = alused;
        this.number = number;
        this.staatus = staatus;
    }
    public String toString() {
        return ("Tellimus nr: " + this.number + " Aluseid: " + this.alused + " Kaal: " + this.kaal + " Staatus: "+ this.staatus);
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public int getKaal() {
        return kaal;
    }
    public void setKaal(int kaal) {
        this.kaal = kaal;
    }
    public int getAlused() {
        return alused;
    }
    public void setAlused(int alused) {
        this.alused = alused;
    }
    public int getStaatus() {
        return staatus;
    }
    public void setStaatus(int staatus) {
        this.staatus = staatus;
    }
}

