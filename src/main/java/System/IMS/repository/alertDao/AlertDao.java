package System.IMS.repository.alertDao;

import System.IMS.entity.Alert;

import java.util.List;

public interface AlertDao {
    Alert save(Alert alert);
    Alert findById(Long id);
    List<Alert> findAll();
    Alert update(Alert alert);
    void delete(Alert alert);
}
