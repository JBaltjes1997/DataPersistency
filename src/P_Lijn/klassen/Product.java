package P_Lijn.klassen;

public class Product {
    public int product_nummer;
    public String naam;
    public String beschrijving;
    public double prijs;
    public OVChipkaart ovchipkaart;

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

    public OVChipkaart getOvchipkaart(){
        return ovchipkaart;
    }

    public String toString(){
        return "p_nummer: " + product_nummer + " naam: " + naam + " beschrijving: " +
                beschrijving + " prijs: " + prijs + " " + ovchipkaart.toString();
    }
}
