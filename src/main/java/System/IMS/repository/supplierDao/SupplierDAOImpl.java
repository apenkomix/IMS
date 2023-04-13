package System.IMS.repository.supplierDao;

import System.IMS.entity.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class SupplierDAOImpl implements SupplierDAO {
    @Autowired
    private EntityManager entityManager;

    public SupplierDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void saveOrUpdate(Supplier supplier) {
        entityManager.getTransaction().begin();
        entityManager.merge(supplier);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Supplier supplier) {
        entityManager.getTransaction().begin();
        entityManager.remove(supplier);
        entityManager.getTransaction().commit();
    }

    @Override
    public Supplier getById(Long id) {
        return entityManager.find(Supplier.class, id);
    }

    @Override
    public List<Supplier> getAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Supplier> query = builder.createQuery(Supplier.class);
        Root<Supplier> root = query.from(Supplier.class);
        query.select(root);
        TypedQuery<Supplier> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }
}
