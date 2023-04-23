package System.IMS.repository.activityLogDao;

import System.IMS.entity.ActivityLog;
import System.IMS.entity.User;


import java.util.List;

public interface ActivityLogDao {
    void save(ActivityLog activityLog);

    ActivityLog findById(Long id);

    List<ActivityLog> findAll();

    ActivityLog update(ActivityLog activityLog);

    void delete(ActivityLog activityLog);

    List<ActivityLog> findByUser(User user);
}
