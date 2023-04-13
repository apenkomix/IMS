package System.IMS.repository.invoiceDao;

import System.IMS.entity.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class InvoiceDaoImpl implements InvoiceDao {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Invoice> getAllInvoices() {
        String jpql = "SELECT i FROM Invoice i";
        TypedQuery<Invoice> query = entityManager.createQuery(jpql, Invoice.class);
        return query.getResultList();
    }

    @Override
    public Invoice getInvoiceById(Long id) {
        return entityManager.find(Invoice.class, id);
    }

    @Override
    public void addInvoice(Invoice invoice) {
        entityManager.persist(invoice);
    }

    @Override
    public void updateInvoice(Invoice invoice) {
        entityManager.merge(invoice);
    }

    @Override
    public void deleteInvoice(Invoice invoice) {
        entityManager.remove(invoice);
    }

    @Override
    public List<Invoice> getInvoicesByStatus(String status) {
        String jpql = "SELECT i FROM Invoice i WHERE i.status = :status";
        TypedQuery<Invoice> query = entityManager.createQuery(jpql, Invoice.class);
        query.setParameter("status", status);
        return query.getResultList();
    }
}
