package System.IMS.repository.activityLogDao;

import System.IMS.entity.ActivityLog;
import System.IMS.entity.User;

import java.util.List;

public interface ActivityLogDao {
    ActivityLog save(ActivityLog activityLog);

    ActivityLog findById(Long id);

    List<ActivityLog> findAll();

    List<ActivityLog> findByUser(User user);

    void delete(ActivityLog activityLog);
}
