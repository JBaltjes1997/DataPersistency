package P_Lijn.DAOPsql;

import P_Lijn.DAO.OVChipkaartDAO;
import P_Lijn.DAO.ProductDAO;
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
        } catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Product> findByOVChipkaart() {
        return null;
    }

    @Override
    public List<Product> findAll(){
        List<Product> producten = new ArrayList<>();
        try {
            String query = "SELECT * FROM product";
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                producten.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4)));
            }
            return producten;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
