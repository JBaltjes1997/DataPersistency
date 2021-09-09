package klassen;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO{
    private Connection conn;

    public ReizigerDAOPsql(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean save(Reiziger reiziger) throws SQLException {
        String query = "INSERT INTO reiziger(id, voorletters, tussenvoegsel, achternaam, geboortedatum) values(?,?,?,?,?)";
        PreparedStatement st = conn.prepareStatement(query);
        st.setInt(1, reiziger.getReiziger_id());
        st.setString(2, reiziger.getVoorletters());
        st.setString(3, reiziger.getTussenvoegsel());
        st.setString(4, reiziger.getAchternaam());
        st.setDate(5, java.sql.Date.valueOf(reiziger.getGeboortedatum()));
        try{
            st.executeQuery(query);
            return true;
        } catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Reiziger reiziger)throws SQLException{
        String query = "UPDATE reiziger SET ? WHERE ?";
        PreparedStatement st = conn.prepareStatement(query);
        st.setObject(1, reiziger);
        st.setObject(2, reiziger);
        try{
            st.executeQuery(query);
            return true;
        } catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Reiziger reiziger) throws SQLException {
        String query = "DELETE ? WHERE naam = ? ";
        PreparedStatement st = conn.prepareStatement(query);
        st.setObject(1, reiziger);
        st.setObject(2, reiziger.getVoorletters());
        try{
            st.executeQuery(query);
            return true;
        } catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Reiziger findById(int id)throws SQLException{
        String query = "SELECT * FROM reiziger WHERE reiziger_id = ? ";
        PreparedStatement st = conn.prepareStatement(query);
        st.setInt(1, id);
        return null;
    }

    @Override
    public List<Reiziger> findByGbDatum(String datum)throws SQLException{
        ArrayList<Reiziger> reizigers = new ArrayList<>();
        String query = "SELECT * FROM reiziger WHERE geboortedatum = ?";
        PreparedStatement st = conn.prepareStatement(query);
        st.setDate(1, Date.valueOf(datum));
        return reizigers;
    }

    @Override
    public List<Reiziger> findAll()throws SQLException{
        ArrayList<Reiziger> reizigers = new ArrayList<>();
        String query = "SELECT * FROM reiziger";
        PreparedStatement st = conn.prepareStatement(query);
//        reizigers.add();
        return reizigers;
    }
}
