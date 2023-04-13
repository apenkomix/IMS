package System.IMS.repository.transferRequestDao;

import System.IMS.entity.TransferRequest;

import java.util.List;

public interface TransferRequestDAO {
    TransferRequest findById(Long id);
    List<TransferRequest> findAll();
    void save(TransferRequest transferRequest);
    void update(TransferRequest transferRequest);
    void delete(TransferRequest transferRequest);
}
