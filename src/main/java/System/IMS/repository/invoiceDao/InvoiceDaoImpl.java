package System.IMS.repository.invoiceDao;

import System.IMS.entity.Invoice;
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
public class InvoiceDaoImpl implements InvoiceDao {
    private SessionFactory sessionFactory;

    @Autowired
    public InvoiceDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Invoice> getAllInvoices() {
        Session session = sessionFactory.getCurrentSession();
        Query<Invoice> query = session.createQuery("FROM Invoice", Invoice.class);
        return query.getResultList();
    }

    @Override
    public Invoice getInvoiceById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Invoice.class, id);
    }

    @Override
    public void addInvoice(Invoice invoice) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(invoice);
    }

    @Override
    public void updateInvoice(Invoice invoice) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(invoice);
    }

    @Override
    public void deleteInvoice(Invoice invoice) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(invoice);
    }

    @Override
    public List<Invoice> getInvoicesByStatus(String status) {
        Session session = sessionFactory.getCurrentSession();
        Query<Invoice> query = session.createQuery("FROM Invoice WHERE status = :status", Invoice.class);
        query.setParameter("status", status);
        return query.getResultList();
    }
}