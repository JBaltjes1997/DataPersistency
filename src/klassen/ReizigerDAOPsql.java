package klassen;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO {
    private Connection conn;

    public ReizigerDAOPsql(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean save(Reiziger reiziger) throws SQLException {
        try{
            String query = "INSERT INTO reiziger(reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum) values(?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, reiziger.getReiziger_id());
            st.setString(2, reiziger.getVoorletters());
            st.setString(3, reiziger.getTussenvoegsel());
            st.setString(4, reiziger.getAchternaam());
            st.setDate(5, (Date) reiziger.getGeboortedatum());
            st.executeQuery(query);
            return true;
        } catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Reiziger reiziger)throws SQLException{
        try{
            String query = "UPDATE reiziger SET reiziger_id = ? voorletters = ? tussenvoegsel = ? achternaam = ? geboortedatum = ? " +
                    "WHERE reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum";
            PreparedStatement st = conn.prepareStatement(query);

            st.setInt(1, reiziger.getReiziger_id());
            st.setString(2, reiziger.getVoorletters());
            st.setString(3, reiziger.getTussenvoegsel());
            st.setString(4, reiziger.getAchternaam());
            st.setDate(5, (Date) reiziger.getGeboortedatum());
            st.executeUpdate(query);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Reiziger reiziger) throws SQLException {
        try{
            String query = "DELETE from reiziger WHERE naam = ? ";
            PreparedStatement st = conn.prepareStatement(query);
            st.setObject(1, reiziger.getVoorletters());
            st.executeUpdate(query);
        } catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Reiziger findById(int id) throws SQLException{
        try {
            String query = "SELECT * FROM reiziger WHERE reiziger_id = ? ";
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);
            if(rs.next()){
                Reiziger reiziger = new Reiziger(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5));
                return reiziger;
            } else{
                return null;
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Reiziger> findByGbDatum(String datum)throws SQLException {
        try {
            ArrayList<Reiziger> reizigers = new ArrayList<>();
            String query = "SELECT * FROM reiziger WHERE geboortedatum = ?";
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                Reiziger reiziger = new Reiziger(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5));
                        reizigers.add(reiziger);
                } return reizigers;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return null;
        }
    }

    @Override
    public List<Reiziger> findAll()throws SQLException {
        try {
            ArrayList<Reiziger> reizigers = new ArrayList<>();
            String query = "SELECT * FROM reiziger";
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                Reiziger reiziger = new Reiziger(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5));
                        reizigers.add(reiziger);
            }
            return reizigers;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
