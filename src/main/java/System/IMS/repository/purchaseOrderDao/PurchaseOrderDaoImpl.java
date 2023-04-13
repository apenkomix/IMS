package System.IMS.repository.purchaseOrderDao;

import System.IMS.entity.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class PurchaseOrderDaoImpl implements PurchaseOrderDao {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<PurchaseOrder> getAllPurchaseOrders() {
        String jpql = "SELECT p FROM PurchaseOrder p";
        TypedQuery<PurchaseOrder> query = entityManager.createQuery(jpql, PurchaseOrder.class);
        return query.getResultList();
    }

    @Override
    public PurchaseOrder getPurchaseOrderById(Long id) {
        return entityManager.find(PurchaseOrder.class, id);
    }

    @Override
    public void addPurchaseOrder(PurchaseOrder purchaseOrder) {
        entityManager.persist(purchaseOrder);
    }

    @Override
    public void updatePurchaseOrder(PurchaseOrder purchaseOrder) {
        entityManager.merge(purchaseOrder);
    }

    @Override
    public void deletePurchaseOrder(PurchaseOrder purchaseOrder) {
        entityManager.remove(purchaseOrder);
    }

    @Override
    public List<PurchaseOrder> getPurchaseOrdersByStatus(String status) {
        String jpql = "SELECT p FROM PurchaseOrder p WHERE p.status = :status";
        TypedQuery<PurchaseOrder> query = entityManager.createQuery(jpql, PurchaseOrder.class);
        query.setParameter("status", status);
        return query.getResultList();
    }
}
