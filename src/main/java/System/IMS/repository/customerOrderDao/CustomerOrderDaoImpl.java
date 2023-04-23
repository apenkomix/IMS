package System.IMS.repository.customerOrderDao;

import System.IMS.entity.CustomerOrder;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public class CustomerOrderDaoImpl implements CustomerOrderDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public CustomerOrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(CustomerOrder customerOrder) {
        Session session = sessionFactory.getCurrentSession();
        session.save(customerOrder);
    }

    @Override
    public void update(CustomerOrder customerOrder) {
        Session session = sessionFactory.getCurrentSession();
        session.update(customerOrder);
    }

    @Override
    public void delete(CustomerOrder customerOrder) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(customerOrder);
    }

    @Override
    public CustomerOrder findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(CustomerOrder.class, id);
    }

    @Override
    public List<CustomerOrder> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CustomerOrder> query = builder.createQuery(CustomerOrder.class);
        Root<CustomerOrder> root = query.from(CustomerOrder.class);
        query.select(root);
        Query<CustomerOrder> q = session.createQuery(query);
        return q.getResultList();
    }
}