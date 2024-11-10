package music.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import music.business.Product;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductDB {

    private static DataSource dataSource;

    static {
        try {
            InitialContext ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/musicDB");
        } catch (NamingException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("DataSource lookup failed.");
        }
    }

    // Method to get a connection from the pool
    private static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // Method to add a product
    public static boolean addProduct(Product product) {
        String sql = "INSERT INTO products (code, description, price) VALUES (?, ?, ?)";
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, product.getCode());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to update a product
    public static boolean updateProduct(Product product) {
        String sql = "UPDATE products SET description = ?, price = ? WHERE code = ?";
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, product.getDescription());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getCode());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to delete a product
    public static boolean deleteProduct(String code) {
        String sql = "DELETE FROM products WHERE code = ?";
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, code);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get all products
    public static List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Product product = new Product();
                product.setCode(rs.getString("code"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
