package P_Lijn.DAOPsql;

import P_Lijn.DAO.OVChipkaartDAO;
import P_Lijn.DAO.ReizigerDAO;
import P_Lijn.klassen.OVChipkaart;
import P_Lijn.klassen.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDAOPsql implements OVChipkaartDAO {
    private Connection conn;
    private ReizigerDAO rdao;

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
            return true;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(OVChipkaart ovchipkaart){
        try{
            String query = "UPDATE ov_chipkaart SET kaart_nummer = ?, geldig_tot = ?, klasse = ?, saldo = ?, reiziger_id = ? " +
                    "WHERE reiziger_id = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, ovchipkaart.getKaart_nummer());
            st.setDate(2, (java.sql.Date) ovchipkaart.getGeldig_tot());
            st.setInt(3, ovchipkaart.getKlasse());
            st.setFloat(4, ovchipkaart.getSaldo());
            st.setInt(5, ovchipkaart.getReiziger_id());
            st.setInt(6, ovchipkaart.getReiziger_id());
            st.executeUpdate();
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(OVChipkaart ovchipkaart){
        try{
            String query = "DELETE from ov_chipkaart WHERE reiziger_id = ? ";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, ovchipkaart.getReiziger_id());
            st.executeUpdate();
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
            while(rs.next()) {  // ovchipkaart moet extra variabele met Reiziger krijgen
                OVChipkaart ovc = new OVChipkaart(rs.getInt(1),
                        rs.getDate(2),
                        rs.getInt(3),
                        rs.getFloat(4),
                        rs.getInt(5));
                ovc.setReiziger(reiziger);
                kaarten.add(ovc);
//                OVChipkaart.setReiziger(rdao.getReiziger_id());

                // in de while-loop de nieuwe ovchipkaart zoeken
                // aan die ene kaart de rdao ovchipkaart.setReiziger(rdao.getReiziger_id())
            }
            return kaarten;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<OVChipkaart> findAll(){
        List<OVChipkaart> ovchipkaarten = new ArrayList<>();
        try {
            String query = "SELECT * FROM ov_chipkaart";
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                OVChipkaart ovc = new OVChipkaart(
                        rs.getInt(1),
                        rs.getDate(2),
                        rs.getInt(3),
                        rs.getFloat(4),
                        rs.getInt(5));
                ovc.setReiziger(rdao.findById(ovc.getReiziger_id()));
                ovchipkaarten.add(ovc);
            } return ovchipkaarten;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
