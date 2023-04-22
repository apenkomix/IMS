package System.IMS.repository.forecastDao;

import System.IMS.entity.Forecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
@Transactional
public class ForecastDaoImpl implements ForecastDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ForecastDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Forecast findById(Long id) {
        String sql = "SELECT * FROM forecast WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new ForecastMapper(), id);
    }

    @Override
    public List<Forecast> findAll() {
        String sql = "SELECT * FROM forecast";
        return jdbcTemplate.query(sql, new ForecastMapper());
    }

    @Override
    public void save(Forecast forecast) {
        String sql = "INSERT INTO forecast (type, content, timestamp) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, forecast.getType(), forecast.getContent(), forecast.getTimestamp());
    }

    @Override
    public void update(Forecast forecast) {
        String sql = "UPDATE forecast SET type = ?, content = ?, timestamp = ? WHERE id = ?";
        jdbcTemplate.update(sql, forecast.getType(), forecast.getContent(), forecast.getTimestamp(), forecast.getId());
    }

    @Override
    public void delete(Forecast forecast) {
        String sql = "DELETE FROM forecast WHERE id = ?";
        jdbcTemplate.update(sql, forecast.getId());
    }

    private static class ForecastMapper implements RowMapper<Forecast> {
        @Override
        public Forecast mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong("id");
            String type = rs.getString("type");
            String content = rs.getString("content");
            Timestamp timestamp = rs.getTimestamp("timestamp");
            return new Forecast(id, type, content, timestamp);
        }
    }
}