package pl.pingwit.basic_spring.repository;

import org.springframework.stereotype.Repository;
import pl.pingwit.basic_spring.controller.product.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private final DataSource dataSource;

    public ProductRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Optional<Product> findProductById(Integer id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT id, name, description, price FROM products WHERE id = ?");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            preparedStatement.setInt(1, id);
            if (resultSet.next()) {
                return Optional.of(toProduct(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> findAllProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM products");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                products.add(toProduct(resultSet));
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer createProduct(Product product) {
        Integer id = getNextProductId();
        String request = """ 
                              INSERT INTO products (id, name, description, price)
                              VALUES (?, ?, ?, ?);
                """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(request)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, product.name());
            preparedStatement.setString(3, product.description());
            preparedStatement.setBigDecimal(4, product.price());
            preparedStatement.executeUpdate();
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Integer getNextProductId() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT nextval('product_id_seq')");
             ResultSet resultSet = preparedStatement.executeQuery()){
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else throw new RuntimeException("Id not found");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProductById(Integer id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM products WHERE id = ?")){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProduct(Product productToUpdate) {
        String updateRequest = """
                UPDATE products
                SET name = ?, description = ?, price = ?
                WHERE id = ?
                """;
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(updateRequest)) {
            preparedStatement.setString(1, productToUpdate.name());
            preparedStatement.setString(2, productToUpdate.description());
            preparedStatement.setBigDecimal(3, productToUpdate.price());
            preparedStatement.setInt(4, productToUpdate.id());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Product toProduct(ResultSet resultSet) throws SQLException {
        return new Product(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getBigDecimal(4));
    }
}