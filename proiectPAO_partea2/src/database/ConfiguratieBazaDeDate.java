package database;

import java.sql.*;

public class ConfiguratieBazaDeDate {
//    private static final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/?user=root"; //"jdbc:mysql://127.0.0.1:3306/proiectpao1";
//    private static final String USER = "root";
//    private static final String PASSWORD = "123456";
//    private static Connection databaseConnection;
//
//    private ConfiguratieBazaDeDate() {}
//
////    public static Connection getDatabaseConnection() {
//public static void main(String[] args) {
//
//        try {
//            if (databaseConnection == null || databaseConnection.isClosed()) {
//                databaseConnection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
//            }
//        }
//        catch (SQLException e) {
//            System.out.println("Error connecting to the database: " + e.getMessage());
//        }
//
////        return databaseConnection;
//    System.out.println(databaseConnection);
//    }
//
//    public static void closeDatabaseConnection() {
//        try {
//            if (databaseConnection != null && !databaseConnection.isClosed()) {
//                databaseConnection.close();
//            }
//        }
//        catch (SQLException e) {
//            System.out.println("Error closing the database: " + e.getMessage());
//        }
//    }

//    Statement statement = connection.createStatement();

    public static void main(String[] args) {

        try {
//            Class.forName( "com.mysql.jdbc.Driver" );
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proiectpao1", "root", "123456");

            Statement statement = connection.createStatement();

//            ResultSet resultSet1 = statement.executeQuery("use proiectpao1");
            ResultSet resultSet = statement.executeQuery("select * from carte1");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("titlu"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}