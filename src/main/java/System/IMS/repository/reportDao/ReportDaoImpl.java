package System.IMS.repository.reportDao;

import System.IMS.entity.Report;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

public class ReportDaoImpl implements ReportDao {
    @Autowired
    private EntityManager entityManager;

    @Override
    public void saveReport(Report report) {
        entityManager.persist(report);
    }

    @Override
    public List<Report> getAllReports() {
        TypedQuery<Report> query = entityManager.createQuery("SELECT r FROM Report r", Report.class);
        return query.getResultList();
    }

    @Override
    public List<Report> getReportsByType(String type) {
        TypedQuery<Report> query = entityManager.createQuery("SELECT r FROM Report r WHERE r.type = :type", Report.class);
        query.setParameter("type", type);
        return query.getResultList();
    }

    @Override
    public List<Report> getReportsByTimestamp(LocalDateTime start, LocalDateTime end) {
        TypedQuery<Report> query = entityManager.createQuery("SELECT r FROM Report r WHERE r.timestamp BETWEEN :start AND :end", Report.class);
        query.setParameter("start", start);
        query.setParameter("end", end);
        return query.getResultList();
    }
}
