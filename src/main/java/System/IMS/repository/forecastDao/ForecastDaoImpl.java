package System.IMS.repository.forecastDao;

import System.IMS.entity.Forecast;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public class ForecastDaoImpl implements ForecastDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public ForecastDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Forecast findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Forecast.class, id);
    }

    @Override
    public List<Forecast> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Forecast> query = builder.createQuery(Forecast.class);
        Root<Forecast> root = query.from(Forecast.class);
        query.select(root);
        return session.createQuery(query).getResultList();
    }

    @Override
    public void save(Forecast forecast) {
        Session session = sessionFactory.getCurrentSession();
        session.save(forecast);
    }

    @Override
    public void update(Forecast forecast) {
        Session session = sessionFactory.getCurrentSession();
        session.update(forecast);
    }

    @Override
    public void delete(Forecast forecast) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(forecast);
    }
}
