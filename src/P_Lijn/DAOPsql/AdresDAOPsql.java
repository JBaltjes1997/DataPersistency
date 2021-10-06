package P_Lijn.DAOPsql;

import P_Lijn.klassen.Adres;
import P_Lijn.DAO.AdresDAO;
import P_Lijn.DAO.ReizigerDAO;
import P_Lijn.klassen.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOPsql implements AdresDAO {
    private Connection conn;
    private ReizigerDAO rdao;

    public AdresDAOPsql(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean save(Adres adres) {
        try{
            String query = "INSERT INTO Adres(adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id ) " +
                    "values(?,?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, adres.getAdres_id());
            st.setString(2, adres.getPostcode());
            st.setString(3, adres.getHuisnummer());
            st.setString(4, adres.getStraat());
            st.setString(5, adres.getWoonplaats());
            st.setInt(6, adres.getReiziger_id());
            st.executeQuery();
            return true;
        } catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Adres adres) {
        try{
            String query = "UPDATE adres SET adres_id = ?, postcode = ?, huisnummer = ?, straat = ?, woonplaats = ?, reiziger_id = ?" +
                    "WHERE adres_id = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, adres.getAdres_id());
            st.setString(2, adres.getPostcode());
            st.setString(3, adres.getHuisnummer());
            st.setString(4, adres.getStraat());
            st.setString(5, adres.getWoonplaats());
            st.setInt(6, adres.getReiziger_id());
            st.setInt(7, adres.getReiziger_id());
            st.executeUpdate();
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        } return true;
    }

    @Override
    public boolean delete(Adres adres) {
        try{
            String query = "DELETE from adres WHERE adres_id = ? ";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, adres.getAdres_id());
            st.executeUpdate();
        } catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
//        try {
//            String query = "SELECT * FROM adres WHERE reiziger_id = ?";
//            PreparedStatement st = conn.prepareStatement(query);
//            ResultSet rs = st.executeQuery();
//            Adres reizigerAdres = new Adres(rs.getInt(1),
//                    rs.getString(2),
//                    rs.getString(3),
//                    rs.getString(4),
//                    rs.getString(5),
//                    rs.getInt(6));
//            return reizigerAdres;
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            return null;
//        }

        try {
            String query = "SELECT * FROM adres WHERE reiziger_id = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, reiziger.getReiziger_id());
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                Adres reizigerAdres = new Adres(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6));
                return reizigerAdres;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    @Override
    public List<Adres> findAll() {
        List<Adres> adressen = new ArrayList<>();
        try{
            String query = "SELECT * FROM adres";
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                adressen.add(new Adres(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6)));
            } return adressen;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}

// kijk