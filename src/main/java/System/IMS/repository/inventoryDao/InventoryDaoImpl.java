package System.IMS.repository.inventoryDao;

import System.IMS.entity.Inventory;
import System.IMS.entity.enams.ValuationMethod;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public class InventoryDaoImpl implements InventoryDao {
    private SessionFactory sessionFactory;

    @Autowired
    public InventoryDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Inventory> getAllInventories() {
        Session session = sessionFactory.getCurrentSession();
        Query<Inventory> query = session.createQuery("FROM Inventory", Inventory.class);
        return query.getResultList();
    }

    @Override
    public Inventory getInventoryById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Inventory.class, id);
    }

    @Override
    public void addInventory(Inventory inventory) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(inventory);
    }

    @Override
    public void updateInventory(Inventory inventory) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(inventory);
    }

    @Override
    public void deleteInventory(Inventory inventory) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(inventory);
    }

    @Override
    public List<Inventory> getInventoryByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query<Inventory> query = session.createQuery("FROM Inventory WHERE name = :name", Inventory.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<Inventory> getInventoryByDescription(String description) {
        Session session = sessionFactory.getCurrentSession();
        Query<Inventory> query = session.createQuery("FROM Inventory WHERE description = :description", Inventory.class);
        query.setParameter("description", description);
        return query.getResultList();
    }

    @Override
    public List<Inventory> getInventoryByLocation(String location) {
        Session session = sessionFactory.getCurrentSession();
        Query<Inventory> query = session.createQuery("FROM Inventory WHERE location = :location", Inventory.class);
        query.setParameter("location", location);
        return query.getResultList();
    }

    @Override
    public List<Inventory> getInventoryByValuationMethod(ValuationMethod valuationMethod) {
        Session session = sessionFactory.getCurrentSession();
        Query<Inventory> query = session.createQuery("FROM Inventory WHERE valuationMethod = :valuationMethod", Inventory.class);
        query.setParameter("valuationMethod", valuationMethod);
        return query.getResultList();
    }
}