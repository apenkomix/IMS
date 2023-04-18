package System.IMS.repository.customerOrderDao;

import System.IMS.entity.CustomerOrder;
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
public class CustomerOrderDaoImpl implements CustomerOrderDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(CustomerOrder customerOrder) {
        entityManager.persist(customerOrder);
    }

    @Override
    public void update(CustomerOrder customerOrder) {
        entityManager.merge(customerOrder);
    }

    @Override
    public void delete(CustomerOrder customerOrder) {
        entityManager.remove(customerOrder);
    }

    @Override
    public CustomerOrder findById(Long id) {
        return entityManager.find(CustomerOrder.class, id);
    }

    @Override
    public List<CustomerOrder> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerOrder> cq = cb.createQuery(CustomerOrder.class);
        Root<CustomerOrder> root = cq.from(CustomerOrder.class);
        cq.select(root);
        TypedQuery<CustomerOrder> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}
