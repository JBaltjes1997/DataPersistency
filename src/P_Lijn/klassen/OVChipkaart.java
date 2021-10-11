package P_Lijn.klassen;

import java.util.Date;
import java.util.List;

public class OVChipkaart {
    public int kaart_nummer;
    public Date geldig_tot;
    public int klasse;
    public float saldo;
    public int reiziger_id;
    public Reiziger reiziger;
    public Product product;

    public OVChipkaart(int kaart_nummer, Date geldig_tot, int klasse, float saldo, int reiziger_id) {
        this.kaart_nummer = kaart_nummer;
        this.geldig_tot = geldig_tot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger_id = reiziger_id;
    }

    public int getKaart_nummer() {
        return kaart_nummer;
    }

    public void setKaart_nummer(int kaart_nummer) {
        this.kaart_nummer = kaart_nummer;
    }

    public Date getGeldig_tot() {
        return geldig_tot;
    }

    public void setGeldig_tot(Date geldig_tot) {
        this.geldig_tot = geldig_tot;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public int getReiziger_id() {
        return reiziger_id;
    }

    public void setReiziger_id(int reiziger_id) {
        this.reiziger_id = reiziger_id;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public Reiziger getReiziger(){
        return reiziger;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct(){
        return product;
    }

    public String toString(){
        return "nummer: " + kaart_nummer + " geldig tot: " + geldig_tot + " klasse: " + klasse +
                " saldo: €" + saldo + " reiziger_id: " + reiziger_id + " " + reiziger.toString();
//        return String.format("Nummer %s geldig tot: %s klasse: %d saldo: € %f reiziger_id: %d");
    }
}
