package System.IMS.repository.billOfMateriaalsDao;

import System.IMS.entity.BillOfMaterials;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class BillOfMaterialsDaoImpl implements BillOfMaterialsDao {
    private final SessionFactory sessionFactory;
    @Autowired
    public BillOfMaterialsDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public BillOfMaterials save(BillOfMaterials billOfMaterials) {
        Session session = sessionFactory.getCurrentSession();
        session.save(billOfMaterials);
        return billOfMaterials;
    }

    @Override
    public BillOfMaterials findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(BillOfMaterials.class, id);
    }

    @Override
    public List<BillOfMaterials> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaQuery<BillOfMaterials> query = session.getCriteriaBuilder().createQuery(BillOfMaterials.class);
        query.from(BillOfMaterials.class);
        return session.createQuery(query).getResultList();
    }

    @Override
    public BillOfMaterials update(BillOfMaterials billOfMaterials) {
        Session session = sessionFactory.getCurrentSession();
        session.update(billOfMaterials);
        return billOfMaterials;
    }

    @Override
    public void delete(BillOfMaterials billOfMaterials) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(billOfMaterials);
    }
}
