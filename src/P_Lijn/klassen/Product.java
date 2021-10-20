package P_Lijn.klassen;

import java.util.ArrayList;

public class Product {
    public int product_nummer;
    public String naam;
    public String beschrijving;
    public double prijs;
    private ArrayList<OVChipkaart> ovchipkaarten = new ArrayList<>();

    public Product(int product_nummer, String naam, String beschrijving, double prijs) {
        this.product_nummer = product_nummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
    }

    public int getProduct_nummer() {
        return product_nummer;
    }

    public void setProduct_nummer(int product_nummer) {
        this.product_nummer = product_nummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public ArrayList<OVChipkaart> getOvchipkaarten() {
        return ovchipkaarten;
    }

    public void setOvchipkaarten(ArrayList<OVChipkaart> ovchipkaarten) {
        this.ovchipkaarten = ovchipkaarten;
    }

    public void addOvchipkaart(OVChipkaart ovchipkaart) {
        if (!ovchipkaarten.contains(ovchipkaart)) {
            ovchipkaarten.add(ovchipkaart);
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_nummer=" + product_nummer +
//                ", ovchipkaarten=" + ovchipkaarten +
                '}';
    }
}
