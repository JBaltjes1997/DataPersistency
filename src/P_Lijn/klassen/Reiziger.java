package P_Lijn.klassen;

import java.util.ArrayList;
import java.util.Date;

public class Reiziger {
    private int reiziger_id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;
    private Adres reizigerAdres;
    private ArrayList<OVChipkaart> ovchipkaarten;

    public Reiziger(int reiziger_id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.reiziger_id = reiziger_id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    public int getReiziger_id() {
        return reiziger_id;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setReiziger_id(int reiziger_id) {
        this.reiziger_id = reiziger_id;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }


    public void setAdres(Adres a){
        reizigerAdres = a;
    }

    public Adres getAdres(){
        return reizigerAdres;
    }


    public void setOvchipkaarten(ArrayList<OVChipkaart> ovchipkaarten) {
        this.ovchipkaarten = ovchipkaarten;
    }

    public ArrayList<OVChipkaart> getOvchipkaarten() {
        return ovchipkaarten;
    }


    public String toString(){
        if(tussenvoegsel == null ) {
            if(reizigerAdres == null){
                return "#" + reiziger_id + ": " + voorletters + ". " + achternaam + " (" + geboortedatum + ") " +
                         " " + ovchipkaarten.toString();
            } else {
                return "#" + reiziger_id + ": " + voorletters + ". " + achternaam + " (" + geboortedatum + ") " +
                        reizigerAdres.toString() + " " + ovchipkaarten.toString();
            }
        } else {
            return "#" + reiziger_id + ": " + voorletters + ". " + tussenvoegsel + " " + achternaam +
                    " (" + geboortedatum + ") " + reizigerAdres.toString() + " " + ovchipkaarten.toString();
        }
    }
}
