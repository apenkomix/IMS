package System.IMS.repository.supplierDao;

import System.IMS.entity.Supplier;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class SupplierDAOImpl implements SupplierDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public SupplierDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveOrUpdate(Supplier supplier) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(supplier);
    }

    @Override
    public void delete(Supplier supplier) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(supplier);
    }

    @Override
    public Supplier getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Supplier.class, id);
    }

    @Override
    public List<Supplier> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder =  sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Supplier> query = builder.createQuery(Supplier.class);
        Root<Supplier> root = query.from(Supplier.class);
        query.select(root);
        Query<Supplier> typedQuery = session.createQuery(query);
        return typedQuery.getResultList();
    }
}