package System.IMS.repository.activityLogDao;

import System.IMS.entity.ActivityLog;
import System.IMS.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Repository
@Transactional
public class ActivityLogDaoImpl implements ActivityLogDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ActivityLogDaoImpl(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(ActivityLog activityLog) {
        String sql = "INSERT INTO activity_log (user_id, activity_type, activity_time) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, activityLog.getUser().getId(), activityLog.getActivity(), activityLog.getTimestamp());
    }

    @Override
    public ActivityLog findById(Long id) {
        String sql = "SELECT * FROM activity_log WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new ActivityLogMapper(), id);
    }

    @Override
    public List<ActivityLog> findAll() {
        String sql = "SELECT * FROM activity_log";
        return jdbcTemplate.query(sql, new ActivityLogMapper());
    }

    @Override
    public ActivityLog update(ActivityLog activityLog) {
        String sql = "UPDATE activity_log SET user_id = ?, activity_type = ?, activity_time = ? WHERE id = ?";
        jdbcTemplate.update(sql, activityLog.getUser().getId(), activityLog.getActivity(), activityLog.getTimestamp(), activityLog.getId());
        return activityLog;
    }

    @Override
    public void delete(ActivityLog activityLog) {
        String sql = "DELETE FROM activity_log WHERE id = ?";
        jdbcTemplate.update(sql, activityLog.getId());
    }

    @Override
    public List<ActivityLog> findByUser(User user) {
        String sql = "SELECT * FROM activity_log WHERE user_id = ?";
        return jdbcTemplate.query(sql, new ActivityLogMapper(), user.getId());
    }

    private static class ActivityLogMapper implements RowMapper<ActivityLog> {

        @Override
        public ActivityLog mapRow(ResultSet rs, int rowNum) throws SQLException {
            ActivityLog activityLog = new ActivityLog();
            activityLog.setId(rs.getLong("id"));
            activityLog.setUser(new User(rs.getLong("user_id")));
            activityLog.setActivity(rs.getString("activity_type"));
            activityLog.setTimestamp(rs.getTimestamp("activity_time").toLocalDateTime());
            return activityLog;
        }
    }
}

