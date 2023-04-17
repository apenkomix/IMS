package System.IMS.repository.activityLogDao;

import System.IMS.entity.ActivityLog;
import System.IMS.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class ActivityLogDaoImpl implements ActivityLogDao {


    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public ActivityLog save(ActivityLog activityLog) {
        entityManager.persist(activityLog);
        return activityLog;
    }

    @Override
    public ActivityLog findById(Long id) {
        return entityManager.find(ActivityLog.class, id);
    }

    @Override
    public List<ActivityLog> findAll() {
        TypedQuery<ActivityLog> query = entityManager.createQuery("SELECT a FROM ActivityLog a", ActivityLog.class);
        return query.getResultList();
    }

    @Override
    public List<ActivityLog> findByUser(User user) {
        TypedQuery<ActivityLog> query = entityManager.createQuery("SELECT a FROM ActivityLog a WHERE a.user = :user", ActivityLog.class);
        query.setParameter("user", user);
        return query.getResultList();
    }

    @Override
    public void delete(ActivityLog activityLog) {
        entityManager.remove(activityLog);
    }
}
