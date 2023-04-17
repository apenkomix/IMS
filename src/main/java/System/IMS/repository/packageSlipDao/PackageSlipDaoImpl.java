package System.IMS.repository.packageSlipDao;

import System.IMS.entity.PackageSlip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class PackageSlipDaoImpl implements PackageSlipDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PackageSlip> getAllPackageSlips() {
        String jpql = "SELECT ps FROM PackageSlip ps";
        TypedQuery<PackageSlip> query = entityManager.createQuery(jpql, PackageSlip.class);
        return query.getResultList();
    }

    @Override
    public PackageSlip getPackageSlipById(Long id) {
        return entityManager.find(PackageSlip.class, id);
    }

    @Override
    public void addPackageSlip(PackageSlip packageSlip) {
        entityManager.persist(packageSlip);
    }

    @Override
    public void updatePackageSlip(PackageSlip packageSlip) {
        entityManager.merge(packageSlip);
    }

    @Override
    public void deletePackageSlip(PackageSlip packageSlip) {
        entityManager.remove(packageSlip);
    }
}
