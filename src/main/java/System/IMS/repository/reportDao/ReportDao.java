package System.IMS.repository.reportDao;

import System.IMS.entity.Report;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportDao {
    void saveReport(Report report);
    List<Report> getAllReports();
    List<Report> getReportsByType(String type);
    List<Report> getReportsByTimestamp(LocalDateTime start, LocalDateTime end);
}
