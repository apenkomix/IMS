package System.IMS.repository.alertDao;

import System.IMS.entity.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class AlertDaoImpl implements AlertDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Alert save(Alert alert) {
        entityManager.persist(alert);
        return alert;
    }

    @Override
    public Alert findById(Long id) {
        return entityManager.find(Alert.class, id);
    }

    @Override
    public List<Alert> findAll() {
        TypedQuery<Alert> query = entityManager.createQuery("SELECT a FROM Alert a", Alert.class);
        return query.getResultList();
    }

    @Override
    public Alert update(Alert alert) {
        return entityManager.merge(alert);
    }

    @Override
    public void delete(Alert alert) {
        entityManager.remove(alert);
    }
}