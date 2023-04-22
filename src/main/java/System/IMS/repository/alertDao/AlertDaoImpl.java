package System.IMS.repository.alertDao;

import System.IMS.entity.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class AlertDaoImpl implements AlertDao {
    private final DataSource dataSource;

    public AlertDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Alert save(Alert alert) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO alert(type, content, timestamp) VALUES (?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, alert.getType());
            statement.setString(2, alert.getContent());
            statement.setTimestamp(3, Timestamp.valueOf(alert.getTimestamp()));
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                alert.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save alert", e);
        }
        return alert;
    }

    @Override
    public Alert findById(Long id) {
        Alert alert = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM alert WHERE id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                alert = new Alert();
                alert.setId(resultSet.getLong("id"));
                alert.setType(resultSet.getString("type"));
                alert.setContent(resultSet.getString("content"));
                alert.setTimestamp(resultSet.getTimestamp("timestamp").toLocalDateTime());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find alert by ID", e);
        }
        return alert;
    }

    @Override
    public List<Alert> findAll() {
        List<Alert> alerts = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM alert")) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Alert alert = new Alert();
                alert.setId(resultSet.getLong("id"));
                alert.setType(resultSet.getString("type"));
                alert.setContent(resultSet.getString("content"));
                alert.setTimestamp(resultSet.getTimestamp("timestamp").toLocalDateTime());
                alerts.add(alert);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find all alerts", e);
        }
        return alerts;
    }

    @Override
    public Alert update(Alert alert) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE alert SET type = ?, content = ?, timestamp = ? WHERE id = ?")) {
            statement.setString(1, alert.getType());
            statement.setString(2, alert.getContent());
            statement.setTimestamp(3, Timestamp.valueOf(alert.getTimestamp()));
            statement.setLong(4, alert.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update alert", e);
        }
        return alert;
    }

    @Override
    public void delete(Alert alert) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM alert WHERE id = ?")) {
            statement.setLong(1, alert.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete alert", e);
        }
    }
}
