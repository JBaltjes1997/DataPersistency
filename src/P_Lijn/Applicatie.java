package P_Lijn;

//
//import Persistency.dataBaseInfo;
//import Persistency.dataBaseQuery;
import P_Lijn.DAO.AdresDAO;
import P_Lijn.DAO.OVChipkaartDAO;
import P_Lijn.DAO.ProductDAO;
import P_Lijn.DAO.ReizigerDAO;
import P_Lijn.DAOPsql.AdresDAOPsql;
import P_Lijn.DAOPsql.OVChipkaartDAOPsql;
import P_Lijn.DAOPsql.ProductDAOPsql;
import P_Lijn.DAOPsql.ReizigerDAOPsql;
import P_Lijn.klassen.Adres;
import P_Lijn.klassen.OVChipkaart;
import P_Lijn.klassen.Product;
import P_Lijn.klassen.Reiziger;
//
//import Persistency.dataBaseQuery;

import java.sql.*;
        import java.util.List;

import static java.sql.DriverManager.getConnection;

public class Applicatie {
    private static Connection conn;

    public static void main(String[] args) throws SQLException {

        ReizigerDAOPsql reizigerDao = new ReizigerDAOPsql(getConnection());
        AdresDAOPsql adresDao = new AdresDAOPsql(getConnection());
        OVChipkaartDAOPsql ovcDAO = new OVChipkaartDAOPsql(getConnection());
        ProductDAOPsql pDAO = new ProductDAOPsql(getConnection());

        reizigerDao.setAdao(adresDao);
        reizigerDao.setOvdao(ovcDAO);
        ovcDAO.setRdao(reizigerDao);
        pDAO.setOvcDAO(ovcDAO);
        ovcDAO.setPdao(pDAO);

//        testReizigerDAO(reizigerDao);
//        testAdresDAO(adresDao, reizigerDao);
//        testOVChipkaartDAO(ovcDAO, reizigerDao);
        testProductDAO(pDAO, ovcDAO);
        closeConnection();
    }

    public static Connection getConnection() throws SQLException {
        return conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/ovchip?user=postgres&password=T1mmyD3Kat");
    }

    private static void closeConnection() throws SQLException {
        conn.close();
    }

    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

//         Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

//         Maak een nieuwe reiziger aan en persisteer deze in de database
        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(77, "S", null, "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");


//        rdao.delete(sietske);
        rdao.findById(6);
    }

    private static void testAdresDAO(AdresDAO adao, ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test AdresDAO -------------");

        //read
        List<Adres> adressen = adao.findAll();
        for (Adres a : adressen) {
            System.out.println(a);
            }
        System.out.println();

//        create
        String gbdatum = "1963-03-15";
        Reiziger Peter = new Reiziger(6, "P", null, "Parker", java.sql.Date.valueOf(gbdatum));
        rdao.save(Peter);
        Adres adr = new Adres(6, "ABCD12", "5", "Somewhere over the Rainbowlaan", "Kansas-City", 6);
        adao.save(adr);
        Peter.setAdres(adr);
        rdao.update(Peter);

//        read
        adao.findByReiziger(Peter);

        //update  check
        adao.update(adr);

        //delete
        adao.delete(adr);


//         bij een persoon moet ook een adres aangemaakt worden?
//         wanneer een persoon gedelete wordt dan ook diens adres
//         idem dito de update
//
//         findBy() moet ook het adres tonen, maar niet anderson

    }

    private static void testOVChipkaartDAO(OVChipkaartDAO ovdao, ReizigerDAO rdao) throws SQLException{
        System.out.println("\n---------- Test OVChipkaartDAO -------------");

        List<OVChipkaart> ovchipkaarten = ovdao.findAll();
        for(OVChipkaart ovc : ovchipkaarten) {
            System.out.println(ovc);
        }
        System.out.println();

        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        String gbdatum = "1963-03-15";
        Reiziger Peter = new Reiziger(6, "P", null, "Parker", java.sql.Date.valueOf(gbdatum));


        //create
        String vervalDatum = "2999-12-31";
        OVChipkaart ovc = new OVChipkaart(00002, java.sql.Date.valueOf(vervalDatum), 2, 10000, 6);

        ovdao.save(ovc);

        //read
        System.out.println(ovdao.findByReiziger(Peter));

        //update
        ovdao.update(ovc);


        //delete
        ovdao.delete(ovc);
    }
//
    private static void testProductDAO(ProductDAO pDAO, OVChipkaartDAO ovdao) throws SQLException {
        System.out.println("\n---------- Test ProductDAO -------------");

//        List<Product> producten = pDAO.findAll();
//        for(Product p : producten) {
//            System.out.println(p);
//        }
//        System.out.println();

//        List<OVChipkaart> ovchipkaarten = ovdao.findAll();
//        for(OVChipkaart ovc : ovchipkaarten) {
//            System.out.println(ovc);
//        }
//        System.out.println();

        String vervalDatum = "2999-12-31";
        OVChipkaart ovc = new OVChipkaart(00002, java.sql.Date.valueOf(vervalDatum), 2, 10000, 6);

//        ovdao.save(ovc);

        Product patent = new Product(7, "CIA", "idk", 0.01);
//
//        pDAO.save(patent);
//
        ovc.addProduct(patent);
//
        System.out.println(pDAO.findByOVChipkaart(ovc));

//        pDAO.update(patent);
//
//        pDAO.delete(patent);
    }
}
