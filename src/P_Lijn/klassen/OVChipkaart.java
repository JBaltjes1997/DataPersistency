package P_Lijn.klassen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OVChipkaart {
    public int kaart_nummer;
    public Date geldig_tot;
    public int klasse;
    public float saldo;
    public int reiziger_id;
    public Reiziger reiziger;
    public ArrayList<Product> producten = new ArrayList<>();

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

    public ArrayList<Product> getProducten() {
        return producten;
    }

    public void setProducten(ArrayList<Product> producten) {
        this.producten = producten;
    }

    public void addProduct(Product product) {
        if (!producten.contains(product)) {
            producten.add(product);
        }
    }

    @Override
    public String toString() {
        return "OVChipkaart{" +
                "kaart_nummer=" + kaart_nummer +
//                ", reiziger=" + reiziger +
//                ", producten=" + producten +
                '}';
    }
}
