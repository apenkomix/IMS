package System.IMS.repository.inventoryDao;

import System.IMS.entity.Inventory;
import System.IMS.entity.enams.ValuationMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class InventoryDaoImpl implements InventoryDao {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Inventory> getAllInventories() {
        TypedQuery<Inventory> query = entityManager.createQuery("SELECT i FROM Inventory i", Inventory.class);
        return query.getResultList();
    }

    @Override
    public Inventory getInventoryById(Long id) {
        return entityManager.find(Inventory.class, id);
    }

    @Override
    public void addInventory(Inventory inventory) {
        entityManager.persist(inventory);
    }

    @Override
    public void updateInventory(Inventory inventory) {
        entityManager.merge(inventory);
    }

    @Override
    public void deleteInventory(Inventory inventory) {
        entityManager.remove(entityManager.contains(inventory) ? inventory : entityManager.merge(inventory));
    }

    @Override
    public List<Inventory> getInventoryByName(String name) {
        TypedQuery<Inventory> query = entityManager.createQuery("SELECT i FROM Inventory i WHERE i.name = :name", Inventory.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<Inventory> getInventoryByDescription(String description) {
        TypedQuery<Inventory> query = entityManager.createQuery("SELECT i FROM Inventory i WHERE i.description = :description", Inventory.class);
        query.setParameter("description", description);
        return query.getResultList();
    }

    @Override
    public List<Inventory> getInventoryByLocation(String location) {
        TypedQuery<Inventory> query = entityManager.createQuery("SELECT i FROM Inventory i WHERE i.location = :location", Inventory.class);
        query.setParameter("location", location);
        return query.getResultList();
    }

    @Override
    public List<Inventory> getInventoryByValuationMethod(ValuationMethod valuationMethod) {
        TypedQuery<Inventory> query = entityManager.createQuery("SELECT i FROM Inventory i WHERE i.valuationMethod = :valuationMethod", Inventory.class);
        query.setParameter("valuationMethod", valuationMethod);
        return query.getResultList();
    }
}
