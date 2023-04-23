package System.IMS.repository.activityLogDao;

import System.IMS.entity.ActivityLog;
import System.IMS.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ActivityLogDaoImpl implements ActivityLogDao {
    private final HibernateTemplate hibernateTemplate;

    @Autowired
    public ActivityLogDaoImpl(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    @Override
    public void save(ActivityLog activityLog) {
        hibernateTemplate.save(activityLog);
    }

    @Override
    public ActivityLog findById(Long id) {
        return hibernateTemplate.get(ActivityLog.class, id);
    }

    @Override
    public List<ActivityLog> findAll() {
        return hibernateTemplate.loadAll(ActivityLog.class);
    }

    @Override
    public ActivityLog update(ActivityLog activityLog) {
        hibernateTemplate.update(activityLog);
        return activityLog;
    }

    @Override
    public void delete(ActivityLog activityLog) {
        hibernateTemplate.delete(activityLog);
    }

    @Override
    public List<ActivityLog> findByUser(User user) {
        String sql = "SELECT * FROM activity_log WHERE user_id = :userId";
        Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createNativeQuery(sql, ActivityLog.class);
        query.setParameter("userId", user.getId());
        List<ActivityLog> activityLogs = query.getResultList();
        return activityLogs;
    }
}
