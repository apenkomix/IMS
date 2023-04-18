package System.IMS.repository.billOfMateriaalsDao;

import System.IMS.entity.BillOfMaterials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class BillOfMaterialsDaoImpl implements BillOfMaterialsDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public BillOfMaterials save(BillOfMaterials billOfMaterials) {
        entityManager.persist(billOfMaterials);
        return billOfMaterials;
    }

    @Override
    public BillOfMaterials findById(Long id) {
        return entityManager.find(BillOfMaterials.class, id);
    }

    @Override
    public List<BillOfMaterials> findAll() {
        TypedQuery<BillOfMaterials> query = entityManager.createQuery("SELECT b FROM BillOfMaterials b", BillOfMaterials.class);
        return query.getResultList();
    }

    @Override
    public BillOfMaterials update(BillOfMaterials billOfMaterials) {
        return entityManager.merge(billOfMaterials);
    }

    @Override
    public void delete(BillOfMaterials billOfMaterials) {
        entityManager.remove(billOfMaterials);
    }
}
