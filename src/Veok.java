/**
 * Created by antz on 19/12/2016.
 */
public class Veok {
    int kandevoime;
    int alusekohti;
    int maxtellimusi;
    int vabaalusekohti;

    public Veok (){
        this.kandevoime = 0;
        this.alusekohti = 0;
        this.maxtellimusi = 0;
        this.vabaalusekohti = 0;
    }

    public Veok(int kandevoime, int alusekohti, int maxtellimusi){
        this.kandevoime = kandevoime;
        this.alusekohti = alusekohti;
        this.maxtellimusi = maxtellimusi;
        this.vabaalusekohti = alusekohti;
    }

    public String toString(){
        return ("Kandevoime: "+ this.kandevoime+" Alusekohti: "+this.alusekohti+ " Maxtellimusi: "+this.maxtellimusi+ " Vabaalusekohti: "+ this.vabaalusekohti);
    }

    public void setKandevoime (int kandevoime){
        this.kandevoime = kandevoime;
    }
    public void setAlusekohti (int alusekohti){
        this.alusekohti = alusekohti;

    }
    public void setMaxtellimusi(int maxtellimusi){
        this.maxtellimusi = maxtellimusi;
    }
    public void setVabaalusekohti (int vabaalusekohti){
        this.vabaalusekohti = vabaalusekohti;
    }

    public int getKandevoime() {
        return kandevoime;
    }

    public int getAlusekohti() {
        return alusekohti;
    }

    public int getMaxtellimusi() {
        return maxtellimusi;
    }

    public int getVabaalusekohti() {
        return vabaalusekohti;
    }
}
