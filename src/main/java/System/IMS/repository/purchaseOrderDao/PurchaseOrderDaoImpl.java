package System.IMS.repository.purchaseOrderDao;

import System.IMS.entity.PurchaseOrder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class PurchaseOrderDaoImpl implements PurchaseOrderDao {
    private final SessionFactory sessionFactory;

    public PurchaseOrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<PurchaseOrder> getAllPurchaseOrders() {
        Session session = sessionFactory.getCurrentSession();
        Query<PurchaseOrder> query = session.createQuery("SELECT p FROM PurchaseOrder p", PurchaseOrder.class);
        return query.getResultList();
    }

    @Override
    public PurchaseOrder getPurchaseOrderById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(PurchaseOrder.class, id);
    }

    @Override
    public void addPurchaseOrder(PurchaseOrder purchaseOrder) {
        Session session = sessionFactory.getCurrentSession();
        session.save(purchaseOrder);
    }

    @Override
    public void updatePurchaseOrder(PurchaseOrder purchaseOrder) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(purchaseOrder);
    }

    @Override
    public void deletePurchaseOrder(PurchaseOrder purchaseOrder) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(purchaseOrder);
    }

    @Override
    public List<PurchaseOrder> getPurchaseOrdersByStatus(String status) {
        Session session = sessionFactory.getCurrentSession();
        Query<PurchaseOrder> query = session.createQuery("SELECT p FROM PurchaseOrder p WHERE p.status = :status", PurchaseOrder.class);
        query.setParameter("status", status);
        return query.getResultList();
    }
}
