package System.IMS.repository.packageSlipDao;

import System.IMS.entity.PackageSlip;
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
public class PackageSlipDaoImpl implements PackageSlipDao {
    private SessionFactory sessionFactory;

    @Autowired
    public PackageSlipDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<PackageSlip> getAllPackageSlips() {
        Session session = sessionFactory.getCurrentSession();
        Query<PackageSlip> query = session.createQuery("FROM PackageSlip", PackageSlip.class);
        return query.getResultList();
    }

    @Override
    public PackageSlip getPackageSlipById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(PackageSlip.class, id);
    }

    @Override
    public void addPackageSlip(PackageSlip packageSlip) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(packageSlip);
    }

    @Override
    public void updatePackageSlip(PackageSlip packageSlip) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(packageSlip);
    }

    @Override
    public void deletePackageSlip(PackageSlip packageSlip) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(packageSlip);
    }
}
