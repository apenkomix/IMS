package System.IMS.repository.billOfMateriaalsDao;

import System.IMS.entity.BillOfMaterials;

import java.util.List;

public interface BillOfMaterialsDao {
    public BillOfMaterials save(BillOfMaterials billOfMaterials);
    public BillOfMaterials findById(Long id);
    public List<BillOfMaterials> findAll();
    public BillOfMaterials update(BillOfMaterials billOfMaterials);
    public void delete(BillOfMaterials billOfMaterials);
}
