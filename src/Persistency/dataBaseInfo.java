//package Persistency;
//
//import klassen.Reiziger;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//
//public class dataBaseInfo {
//    public static ArrayList<Reiziger> getReizigers() throws SQLException {
//        ArrayList<Reiziger> reizigers = new ArrayList<>();
//
//        Connection connection = Persistency.dataBaseQuery.getDBConnection();
//        Statement statement = connection.createStatement();
//
//        ResultSet resultSet = statement.executeQuery("SELECT * FROM reiziger");
//        while(resultSet.next()){
//            Reiziger reiziger = ReizigerFromDatabase(resultSet);
//            reizigers.add(reiziger);
//        }
//        return reizigers;
//    }
//
//    private static Reiziger ReizigerFromDatabase(ResultSet resultSet) throws SQLException{
//        int id_num = resultSet.getInt(1);
//        String voorletters = resultSet.getString(2);
//        String tussenvoegsel = resultSet.getString(3);
//        String achternaam = resultSet.getString(4);
//        String geboortedatum = resultSet.getString(5);
//        Reiziger reiziger = new Reiziger(id_num, voorletters, tussenvoegsel, achternaam, geboortedatum);
//        return reiziger;
//    }
//}
