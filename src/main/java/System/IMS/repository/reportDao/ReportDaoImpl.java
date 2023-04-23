package System.IMS.repository.reportDao;

import System.IMS.entity.Report;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;
@Repository
@Transactional
public class ReportDaoImpl implements ReportDao {
    private final SessionFactory sessionFactory;

    public ReportDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveReport(Report report) {
        sessionFactory.getCurrentSession().save(report);
    }

    @Override
    public List<Report> getAllReports() {
        Session session = sessionFactory.getCurrentSession();
        Query<Report> query = session.createQuery("SELECT r FROM Report r", Report.class);
        return query.getResultList();
    }

    @Override
    public List<Report> getReportsByType(String type) {
        Session session = sessionFactory.getCurrentSession();
        Query<Report> query = session.createQuery("SELECT r FROM Report r WHERE r.type = :type", Report.class);
        query.setParameter("type", type);
        return query.getResultList();
    }

    @Override
    public List<Report> getReportsByTimestamp(LocalDateTime start, LocalDateTime end) {
        Session session = sessionFactory.getCurrentSession();
        Query<Report> query = session.createQuery("SELECT r FROM Report r WHERE r.timestamp BETWEEN :start AND :end", Report.class);
        query.setParameter("start", start);
        query.setParameter("end", end);
        return query.getResultList();
    }
}
