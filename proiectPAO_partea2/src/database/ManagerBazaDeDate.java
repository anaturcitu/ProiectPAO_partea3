//package database;
//
//import model.Address;
//import model.Order;
//import model.Product;
//import model.User;
//import util.Category;
//import util.PayMethod;
//
//import java.sql.*;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class ManagerBazaDeDate {
//    private static ManagerBazaDeDate instance;
//
//    private ManagerBazaDeDate() {}
//
//    public static synchronized ManagerBazaDeDate getInstance() {
//        if (instance == null)
//            instance = new ManagerBazaDeDate();
//
//        return instance;
//    }
//
//    public List<Product> getAllProducts() {
//        String selectSQL = "SELECT * FROM products";
//        Connection connection = ConfiguratieBazaDeDate.getDatabaseConnection();
//        List<Product> products = new ArrayList<>();
//
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(selectSQL);
//
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                String category = resultSet.getString("category");
//                double price = resultSet.getDouble("price");
//
//                products.add(new Product(name, Category.valueOf(category), price, id));
//            }
//        }
//        catch (SQLException e) {
//            System.out.println("Error reading products from database: " + e.getMessage());
//        }
//
//        return products;
//    }
//
//    public void insertProduct(Product product) {
//        String insertSQL = "INSERT INTO products (name, category, price) VALUES (?, ?, ?)";
//        Connection connection = ConfiguratieBazaDeDate.getDatabaseConnection();
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {
//            preparedStatement.setString(1, product.getName());
//            preparedStatement.setString(2, product.getCategory().toString());
//            preparedStatement.setDouble(3, product.getPrice());
//
//            int rowsAffected = preparedStatement.executeUpdate();
//            System.out.println("Rows affected: " + rowsAffected);
//
//            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
//            if (generatedKeys.next()) {
//                int generatedId = generatedKeys.getInt(1);
//                product.setId(generatedId);
//            }
//        } catch (SQLException e) {
//            System.out.println("Error inserting product into database: " + e.getMessage());
//        }
//    }
//
//    public void updateProduct(Product product) {
//        String updateSQL = "UPDATE products SET name = ?, price = ? WHERE id = ?";
//        Connection connection = ConfiguratieBazaDeDate.getDatabaseConnection();
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
//            preparedStatement.setString(1, product.getName());
//            preparedStatement.setDouble(2, product.getPrice());
//            preparedStatement.setInt(3, product.getId());
//
//            int rowsAffected = preparedStatement.executeUpdate();
//            System.out.println("Rows affected: " + rowsAffected);
//        } catch (SQLException e) {
//            System.out.println("Error updating product in database: " + e.getMessage());
//        }
//    }
//
//    public void removeProduct(int productId) {
//        String deleteSQL = "DELETE FROM products WHERE id = ?";
//        Connection connection = ConfiguratieBazaDeDate.getDatabaseConnection();
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
//            preparedStatement.setInt(1, productId);
//
//            int rowsAffected = preparedStatement.executeUpdate();
//            System.out.println("Rows affected: " + rowsAffected);
//        } catch (SQLException e) {
//            System.out.println("Error removing product from database: " + e.getMessage());
//        }
//    }