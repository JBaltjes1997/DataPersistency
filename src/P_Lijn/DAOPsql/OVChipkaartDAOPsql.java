package P_Lijn.DAOPsql;

import P_Lijn.DAO.OVChipkaartDAO;
import P_Lijn.DAO.ProductDAO;
import P_Lijn.DAO.ReizigerDAO;
import P_Lijn.klassen.OVChipkaart;
import P_Lijn.klassen.Product;
import P_Lijn.klassen.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDAOPsql implements OVChipkaartDAO {
    private Connection conn;
    private ReizigerDAO rdao;
    private ProductDAO pdao;

    public OVChipkaartDAOPsql(Connection conn) {
        this.conn = conn;
    }

    public void setRdao(ReizigerDAO rdao) {
        this.rdao = rdao;
    }

    @Override
    public boolean save(OVChipkaart ovchipkaart){
        try{
            String query = "INSERT INTO ov_chipkaart(kaart_nummer, geldig_tot, klasse, saldo, reiziger_id) " +
                    "values(?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, ovchipkaart.getKaart_nummer());
            st.setDate(2, (java.sql.Date) ovchipkaart.getGeldig_tot());
            st.setInt(3, ovchipkaart.getKlasse());
            st.setFloat(4, ovchipkaart.getSaldo());
            st.setInt(5, ovchipkaart.getReiziger_id());
            st.executeQuery();

            if(ovchipkaart.getProducten().size() != 0){
                  for(Product p : ovchipkaart.getProducten()){
                    pdao.update(p);
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
    public boolean update(OVChipkaart ovchipkaart){
        try{
            if(ovchipkaart.getProducten().size() != 0){
                for(Product p : ovchipkaart.getProducten()){
                    pdao.update(p);
                }
            }

            String query = "UPDATE ov_chipkaart SET geldig_tot = ?, klasse = ?, saldo = ?" +
                    "WHERE kaart_nummer = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setDate(1, (java.sql.Date) ovchipkaart.getGeldig_tot());
            st.setInt(2, ovchipkaart.getKlasse());
            st.setFloat(3, ovchipkaart.getSaldo());
            st.setInt(4, ovchipkaart.getKaart_nummer());
            st.executeUpdate();

            st.close();

            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(OVChipkaart ovchipkaart){
        try{

            if(ovchipkaart.getProducten().size() != 0){
                for(Product p : ovchipkaart.getProducten()){
                    pdao.update(p);
                }
            }
            String query = "DELETE from ov_chipkaart WHERE kaart_nummer = ? ";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, ovchipkaart.getKaart_nummer());
            st.executeUpdate();

            st.close();

        } catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger){
        List<OVChipkaart> kaarten = new ArrayList<>();
        try {
            String query = "SELECT * FROM ov_chipkaart WHERE reiziger_id = ? ";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, reiziger.getReiziger_id());
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                OVChipkaart ovc = new OVChipkaart(
                        rs.getInt(1),
                        rs.getDate(2),
                        rs.getInt(3),
                        rs.getFloat(4),
                        rs.getInt(5));
                ovc.setReiziger(reiziger);
                kaarten.add(ovc);
            }
            st.close();
            return kaarten;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<OVChipkaart> findAll(){
        try{
            String query = "SELECT * FROM ov_chipkaart";
            PreparedStatement st = conn.prepareStatement(query);
            List<OVChipkaart> kaarten = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                OVChipkaart ovc = new OVChipkaart(
                        rs.getInt(1),
                        rs.getDate(2),
                        rs.getInt(3),
                        rs.getFloat(4),
                        rs.getInt(5));
                ovc.setReiziger(rdao.findById(rs.getInt(5)));
//                for(Product p : pdao.findByOVChipkaart(ovc)){
//                    ovc.addProduct(p);
//                }
                pdao.findByOVChipkaart(ovc);
                kaarten.add(ovc);
            }

            rs.close();
            st.close();

            return kaarten;

        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
