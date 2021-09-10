package klassen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class AdresDAOPsql implements AdresDAO{
    private Connection conn;
    private AdresDAO adao;

    public AdresDAOPsql(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean save(Adres adres) {
        try{
            String query = "INSERT INTO Adres(id, postcode) values(?,?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, adres.getId());
            st.setString(2, adres.getPostcode());
            st.executeQuery(query);
            return true;
        } catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Adres adres) {
        return false;
    }

    @Override
    public boolean delete(Adres adres) {
        return false;
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        return null;
    }

    @Override
    public List<Adres> findAll() {
        return null;
    }
}
