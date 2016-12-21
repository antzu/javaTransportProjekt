/**
 * Created by antz on 27/11/2016.
 */
public class Tellimus {
    Integer number;
    Integer kaal;
    Integer alused;
    Integer staatus;
    Integer koormanr;

    public Tellimus (){
        this.kaal = 0;
        this.alused = 0;
        this.number = 0;
        this.staatus = 0;
        this.koormanr = null;
    }

    public Tellimus(int kaal, int alused, int number, int staatus, int koormanr) {
        this.kaal = kaal;
        this.alused = alused;
        this.number = number;
        this.staatus = staatus;
        this.koormanr = koormanr;
    }
    public String toString() {
        return ("Tellimus nr: " + this.number + " Aluseid: " + this.alused + " Kaal: " + this.kaal + " Staatus: "+ this.staatus + " Koormanr: "+ this.koormanr);
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

    public Integer getKoormanr() {
        return koormanr;
    }

    public void setKoormanr(Integer koormanr) {
        this.koormanr = koormanr;
    }

    public void setStaatus(int staatus) {
        this.staatus = staatus;
    }
}

