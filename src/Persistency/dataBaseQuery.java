//package Persistency;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//import static java.sql.DriverManager.getConnection;
//
//    public class dataBaseQuery {
//        private static String jdbcURL = "jdbc:postgresql://localhost:5433/FishySystem";
//        private static String username = "postgres";
//        private static String password = "T1mmyD3Kat";
//        private static Connection connection;
//
//        public static void setDBConnection() throws SQLException {
//            dataBaseQuery.connection = getConnection(jdbcURL, username, password );
//        }
//
//        public static Connection getDBConnection() {
//            return connection;
//        }
//
//        public static void closeDBConnection() throws SQLException {
//        connection.close();
//        }
//    }
