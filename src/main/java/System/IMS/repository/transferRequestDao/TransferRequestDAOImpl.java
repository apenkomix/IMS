package System.IMS.repository.transferRequestDao;

import System.IMS.entity.TransferRequest;
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
public class TransferRequestDAOImpl implements TransferRequestDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public TransferRequest findById(Long id) {
        return entityManager.find(TransferRequest.class, id);
    }

    @Override
    public List<TransferRequest> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TransferRequest> cq = cb.createQuery(TransferRequest.class);
        Root<TransferRequest> rootEntry = cq.from(TransferRequest.class);
        CriteriaQuery<TransferRequest> all = cq.select(rootEntry);
        TypedQuery<TransferRequest> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public void save(TransferRequest transferRequest) {
        entityManager.persist(transferRequest);
    }

    @Override
    public void update(TransferRequest transferRequest) {
        entityManager.merge(transferRequest);
    }

    @Override
    public void delete(TransferRequest transferRequest) {
        entityManager.remove(entityManager.contains(transferRequest) ? transferRequest : entityManager.merge(transferRequest));
    }
}

