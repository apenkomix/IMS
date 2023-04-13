package System.IMS.repository.inventoryDao;

import System.IMS.entity.Inventory;
import System.IMS.entity.enams.ValuationMethod;

import java.util.List;

public interface InventoryDao {
    List<Inventory> getAllInventories();
    Inventory getInventoryById(Long id);
    void addInventory(Inventory inventory);
    void updateInventory(Inventory inventory);
    void deleteInventory(Inventory inventory);
    List<Inventory> getInventoryByName(String name);
    List<Inventory> getInventoryByDescription(String description);
    List<Inventory> getInventoryByLocation(String location);
    List<Inventory> getInventoryByValuationMethod(ValuationMethod valuationMethod);
}
