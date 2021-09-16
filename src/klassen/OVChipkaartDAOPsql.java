package klassen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDAOPsql {
    private Connection conn;
    private Reiziger rdao;

    public OVChipkaartDAOPsql(Connection conn) {
        this.conn = conn;
    }

    public boolean save(){
        return false;
    }

    public boolean update(){
        return false;
    }

    public boolean delete(){
        return false;
    }


    public List<OVChipkaart> findByReiziger(Reiziger reiziger){
        List<OVChipkaart> kaarten = new ArrayList<>();
        try {
            String query = "SELECT * FROM ov_chipkaart";
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                kaarten.add(new OVChipkaart(rs.getInt(1),
                        rs.getDate(2),
                        rs.getInt(3),
                        rs.getFloat(4),
                        rs.getInt(5)));
            } return kaarten;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<OVChipkaart> findAll(Reiziger reiziger){
        return null;
    }
}
