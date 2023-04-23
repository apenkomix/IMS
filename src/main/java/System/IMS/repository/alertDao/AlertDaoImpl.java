package System.IMS.repository.alertDao;

import System.IMS.entity.Alert;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Alert save(Alert alert) {
        sessionFactory.getCurrentSession().save(alert);
        return alert;
    }

    @Override
    public Alert findById(Long id) {
        return sessionFactory.getCurrentSession().get(Alert.class, id);
    }

    @Override
    public List<Alert> findAll() {
        CriteriaBuilder cb = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Alert> cq = cb.createQuery(Alert.class);
        Root<Alert> root = cq.from(Alert.class);
        cq.select(root);
        Query<Alert> query = sessionFactory.getCurrentSession().createQuery(cq);
        return query.getResultList();
    }

    @Override
    public Alert update(Alert alert) {
        sessionFactory.getCurrentSession().update(alert);
        return alert;
    }

    @Override
    public void delete(Alert alert) {
        sessionFactory.getCurrentSession().delete(alert);
    }

}