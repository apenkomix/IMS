package System.IMS.repository.supplierDao;

import System.IMS.entity.Supplier;

import java.util.List;

public interface SupplierDAO {
    void saveOrUpdate(Supplier supplier);
    void delete(Supplier supplier);
    Supplier getById(Long id);
    List<Supplier> getAll();
}
