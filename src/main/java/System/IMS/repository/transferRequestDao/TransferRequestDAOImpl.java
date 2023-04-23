package System.IMS.repository.transferRequestDao;

import System.IMS.entity.TransferRequest;

import jakarta.persistence.TypedQuery;
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
public class TransferRequestDAOImpl implements TransferRequestDAO {
    private SessionFactory sessionFactory;
    @Autowired
    public TransferRequestDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public TransferRequest findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(TransferRequest.class, id);
    }

    @Override
    public List<TransferRequest> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<TransferRequest> cq = cb.createQuery(TransferRequest.class);
        Root<TransferRequest> rootEntry = cq.from(TransferRequest.class);
        CriteriaQuery<TransferRequest> all = cq.select(rootEntry);
        TypedQuery<TransferRequest> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public void save(TransferRequest transferRequest) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(transferRequest);
    }

    @Override
    public void update(TransferRequest transferRequest) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(transferRequest);
    }

    @Override
    public void delete(TransferRequest transferRequest) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.contains(transferRequest) ? transferRequest : session.merge(transferRequest));
    }
}

