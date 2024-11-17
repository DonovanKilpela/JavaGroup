package music.data;

import music.business.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ProductDB {

    private static Connection getConnection() throws Exception {
        Context initialContext = new InitialContext();
        Context envContext = (Context) initialContext.lookup("java:/comp/env");
        DataSource ds = (DataSource) envContext.lookup("jdbc/musicDB");
        return ds.getConnection();
    }

    public static List<Product> selectProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getLong("product_id"));
                product.setCode(rs.getString("code"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));

                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    public static Product selectProduct(String code) {
        Product product = null;
        String query = "SELECT * FROM products WHERE code = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, code);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    product = new Product();
                    product.setProductId(rs.getLong("product_id"));
                    product.setCode(rs.getString("code"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getDouble("price"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }

    public static void insertProduct(Product product) {
        String query = "INSERT INTO products (code, description, price) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, product.getCode());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateProduct(Product product) {
        String query = "UPDATE products SET description = ?, price = ? WHERE code = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, product.getDescription());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getCode());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteProduct(String code) {
        String query = "DELETE FROM products WHERE code = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, code);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
