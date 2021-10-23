package P_Lijn.DAOPsql;

import P_Lijn.DAO.OVChipkaartDAO;
import P_Lijn.DAO.ProductDAO;
import P_Lijn.klassen.OVChipkaart;
import P_Lijn.klassen.Product;
import P_Lijn.klassen.Reiziger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOPsql implements ProductDAO {
    private Connection conn;
    private OVChipkaartDAO ovcDAO;

    public ProductDAOPsql(Connection conn) {
        this.conn = conn;
    }

    public void setOvcDAO(OVChipkaartDAO ovcDAO) {
        this.ovcDAO = ovcDAO;
    }

    @Override
    public boolean save(Product product) {
        try{
            String query = "INSERT INTO product(product_nummer, naam, beschrijving, prijs) " +
                    "values(?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, product.getProduct_nummer());
            st.setString(2, product.getNaam());
            st.setString(3, product.getBeschrijving());
            st.setDouble(4, product.getPrijs());
            st.executeQuery();

            if(product.getOvchipkaarten().size() != 0){
                for(OVChipkaart ovc : product.getOvchipkaarten()){
                    String query2 = "INSERT INTO ov_chipkaart(kaart_nummer, geldig_tot, klasse, saldo, reiziger_id) " +
                            "values(?,?,?,?,?)";
                    PreparedStatement st2 = conn.prepareStatement(query2);
                    st2.setInt(1, ovc.getKaart_nummer());
                    st2.setDate(2, (java.sql.Date) ovc.getGeldig_tot());
                    st2.setInt(3, ovc.getKlasse());
                    st2.setFloat(4, ovc.getSaldo());
                    st2.setInt(5, ovc.getReiziger_id());
                    st2.executeQuery();
                    st2.close();
                }
            }

            st.close();

            return true;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Product product) {
        try {
            String query = "UPDATE product SET product_nummer = ?, naam = ?, beschrijving = ?, prijs = ?" +
                    "WHERE product_nummer = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, product.getProduct_nummer());
            st.setString(2, product.getNaam());
            st.setString(3, product.getBeschrijving());
            st.setDouble(4, product.getPrijs());
            st.setInt(5, product.getProduct_nummer());
            st.executeUpdate();

            if (product.getOvchipkaarten().size() != 0) {
                for (OVChipkaart ovc : product.getOvchipkaarten()) {
                    String query2 = "DELETE FROM ov_chipkaart_product " +
                            "WHERE kaart_nummer = ? AND product_nummer = ?";
                    PreparedStatement st2 = conn.prepareStatement(query2);
                    st2.setInt(1, ovc.getKaart_nummer());
                    st2.setInt(2, product.getProduct_nummer());
                    st2.executeQuery();
                    st2.close();
                }

                for (OVChipkaart ovc : product.getOvchipkaarten()) {
                    String query3 = "INSERT INTO ov_chipkaart_product " +
                            "(kaart_nummer, product_nummer), VALUES(?,?) ";
                    PreparedStatement st3 = conn.prepareStatement(query3);
                    st3.setInt(1, ovc.getKaart_nummer());
                    st3.setInt(2, product.getProduct_nummer());
                    st3.executeQuery();
                    st3.close();
                }
            }

            st.close();

            return true;

            } catch(Exception e){
                System.out.println(e.getMessage());
                return false;
            }
        }

    @Override
    public boolean delete(Product product) {
        try{
            if(product.getOvchipkaarten().size() != 0){
                for(OVChipkaart ovc : product.getOvchipkaarten()){
                    String query2 = "DELETE FROM ov_chipkaart_product " +
                            "where kaart_nummer = ? and product_nummer = ?";
                    PreparedStatement st = conn.prepareStatement(query2);
                    st.setInt(1, ovc.getKaart_nummer());
                    st.setInt(2, product.getProduct_nummer());
                    st.executeQuery(query2);
                    st.close();
                }
            }

            String query = "DELETE from product WHERE product_nummer = ? ";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, product.getProduct_nummer());
            st.executeUpdate();

            ArrayList<OVChipkaart> ovKaarten = product.getOvchipkaarten();
            for(OVChipkaart ovc : ovKaarten){
                if (ovKaarten.size() != 0) {
                    ovcDAO.delete(ovc);
                }
            }

        } catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Product> findByOVChipkaart(OVChipkaart ovChipkaart) {
        ArrayList<Product> producten = new ArrayList<>();
        try {
//            String query = "select product.product_nummer, naam, beschrijving, prijs " +
//                    "from product" +
//                    "inner join ov_chipkaart_product" +
//                    "on ov_chipkaart_product.product_nummer = product.product_nummer " +
//                    "and ov_chipkaart_product.kaart_nummer = ?";

            String query = "select product.product_nummer, naam, beschrijving, prijs " +
                    "from product" +
                    " inner join ov_chipkaart_product" +
                    " on ov_chipkaart_product.product_nummer = product.product_nummer " +
                    "and ov_chipkaart_product.kaart_nummer = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, ovChipkaart.getKaart_nummer());
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Product p = new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getFloat(4));
                p.addOvchipkaart(ovChipkaart);
                ovChipkaart.addProduct(p);
                producten.add(p);
            }

            st.close();
            rs.close();

            return producten;

        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public List<Product> findAll(){

        List<Product> producten = new ArrayList<>();
        try {
            String query = "SELECT * FROM product";
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Product p = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4));
                producten.add(p);
            }

            rs.close();
            st.close();
            return producten;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
