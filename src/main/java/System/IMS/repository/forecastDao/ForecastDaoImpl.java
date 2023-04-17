package System.IMS.repository.forecastDao;

import System.IMS.entity.Forecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class ForecastDaoImpl implements ForecastDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Forecast findById(Long id) {
        return entityManager.find(Forecast.class, id);
    }

    @Override
    public List<Forecast> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Forecast> cq = cb.createQuery(Forecast.class);
        Root<Forecast> root = cq.from(Forecast.class);
        cq.select(root);
        TypedQuery<Forecast> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void save(Forecast forecast) {
        entityManager.persist(forecast);
    }

    @Override
    public void update(Forecast forecast) {
        entityManager.merge(forecast);
    }

    @Override
    public void delete(Forecast forecast) {
        entityManager.remove(entityManager.contains(forecast) ? forecast : entityManager.merge(forecast));
    }
}
