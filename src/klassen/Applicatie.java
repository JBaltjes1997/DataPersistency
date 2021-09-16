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
////        testReizigerDAO(reizigerDao);
        AdresDAO adresDao = new AdresDAOPsql(getConnection());
        testAdresDAO(adresDao, reizigerDao);
        closeConnection();
    }
    public static Connection getConnection()throws SQLException{
        return conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/ovchip?user=postgres&password=T1mmyD3Kat");
    }

    private static void closeConnection()throws SQLException{
        conn.close();
    }

//    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
//        System.out.println("\n---------- Test ReizigerDAO -------------");
//
//        // Haal alle reizigers op uit de database
//        List<Reiziger> reizigers = rdao.findAll();
//        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
//        for (Reiziger r : reizigers) {
//            System.out.println(r);
//        }
//        System.out.println();
//
//        // Maak een nieuwe reiziger aan en persisteer deze in de database
//        String gbdatum = "1981-03-14";
//        Reiziger sietske = new Reiziger(77, "S", null, "Boers", java.sql.Date.valueOf(gbdatum));
//        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
//        rdao.save(sietske);
//        reizigers = rdao.findAll();
//        System.out.println(reizigers.size() + " reizigers\n");
//
//        rdao.delete(sietske);
//
//        // Voeg aanvullende tests van de ontbrekende CRUD-operaties in.
//    }

    private static void testAdresDAO(AdresDAO adao, ReizigerDAO rdao) throws SQLException{
        System.out.println("\n---------- Test AdresDAO -------------");

        List<Adres> adressen = adao.findAll();
        System.out.println(adressen);

        String gbdatum = "1963-03-02";
        Reiziger Pieter = new Reiziger(6, "P", null, "Parker", java.sql.Date.valueOf(gbdatum));
        rdao.save(Pieter);

        //create
        Adres adr = new Adres(6, "ABCD12", "0A", "Somewhere over the Rainbowlaan", "Kansas-City", 6);
        adao.save(adr);

        //read
        adao.findAll();

        //update
        adao.update(adr);

        //delete
        adao.delete(adr);
        rdao.delete(Pieter);
    }
}
