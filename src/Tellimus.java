/**
 * Created by antz on 27/11/2016.
 */
public class Tellimus {
    Integer number;
    Integer kaal;
    Integer alused;
    Integer staatus;

    public Tellimus(int kaal, int alused, int number) {
        this.kaal = kaal;
        this.alused = alused;
        this.number = number;
        this.staatus = 0;
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

