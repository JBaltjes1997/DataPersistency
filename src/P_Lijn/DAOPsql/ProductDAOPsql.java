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

            if(product.getOvchipkaarten().size() >= 1){
                for(OVChipkaart ovc : product.getOvchipkaarten()){
                    ovcDAO.save(ovc);
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
        try{
            String query = "UPDATE product SET product_nummer = ?, naam = ?, beschrijving = ?, prijs = ?" +
                    "WHERE product_nummer = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, product.getProduct_nummer());
            st.setString(2, product.getNaam());
            st.setString(3, product.getBeschrijving());
            st.setDouble(4, product.getPrijs());
            st.setInt(5, product.getProduct_nummer());
            st.executeUpdate();


            if(product.getOvchipkaarten().size() >= 1) {
                for (OVChipkaart ovc : product.getOvchipkaarten()) {
                    ovcDAO.update(ovc);
                }
            }

            st.close();

            return true;

        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Product product) {
        try{
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
            String query = "select product.product_nummer, naam, beschrijving, prijs\n" +
                    "from product\n" +
                    "join ov_chipkaart_product\n" +
                    "on ov_chipkaart_product.product_nummer = product.product_nummer\n" +
                    "where ov_chipkaart_product.kaart_nummer = ?";
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
                producten.add(p);
            }

            st.close();

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
//                p.addOvchipkaart(ovcDAO.find);

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
