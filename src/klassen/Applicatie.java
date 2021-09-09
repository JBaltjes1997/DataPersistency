package klassen;

import java.time.LocalDate;
import java.util.ArrayList;
//
//import Persistency.dataBaseInfo;
//import Persistency.dataBaseQuery;
import klassen.Reiziger;
//
//import Persistency.dataBaseQuery;

import java.sql.*;
import java.util.Date;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class Applicatie {
    private static Connection conn;

    public static void main(String[] args) throws SQLException {

        ReizigerDAO reizigerDao = new ReizigerDAOPsql(getConnection());
        testReizigerDAO(reizigerDao);
//        String url = "jdbc:postgresql://localhost:5433/ovchip?user=postgres&password=T1mmyD3Kat";
        closeConnection();
    }
    public static Connection getConnection()throws SQLException{
        return conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/ovchip?user=postgres&password=T1mmyD3Kat");
    }

    private static void closeConnection()throws SQLException{
        conn.close();
    }

    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        // Maak een nieuwe reiziger aan en persisteer deze in de database
        LocalDate gbdatum = LocalDate.of(1981, 03,14);
        Reiziger sietske = new Reiziger(77, "S", "", "Boers", gbdatum);
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        // Voeg aanvullende tests van de ontbrekende CRUD-operaties in.
    }
}
//        try{
//            Connection conn = DriverManager.getConnection(url);
//
//            Statement st = conn.createStatement();
//            String query = "SELECT * FROM reiziger";
//
//            ResultSet rs = st.executeQuery(query);
//
//            int id;
//            String naam;
//            String tussenvoegsel;
//            String achternaam;
//            Date geboortedatum;
//
//            System.out.println("Alle reizigers: ");
//            while(rs.next()){
//                id = rs.getInt("reiziger_id");
//                naam = rs.getString("voorletters");
//                tussenvoegsel = rs.getString("tussenvoegsel");
//                achternaam = rs.getString("achternaam");
//                geboortedatum = rs.getDate("geboortedatum");
//
//                if(tussenvoegsel == null ) {
//                    System.out.println("    #" + id + ": " + naam + ". " + achternaam + " (" + geboortedatum + ")");
//                } else {
//                    System.out.println("    #" + id + ": " + naam + ". " + tussenvoegsel + " " + achternaam + " (" + geboortedatum + ")");
//                }
//            }
//
//            rs.close();
//            st.close();
//            conn.close();
//
//        } catch (SQLException sqlex){
//            System.out.println("Niet gelukt " + sqlex.getMessage());
//
//        }
//    }
//}

