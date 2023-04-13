package System.IMS.repository.purchaseOrderDao;

import System.IMS.entity.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderDao {
    List<PurchaseOrder> getAllPurchaseOrders();
    PurchaseOrder getPurchaseOrderById(Long id);
    void addPurchaseOrder(PurchaseOrder purchaseOrder);
    void updatePurchaseOrder(PurchaseOrder purchaseOrder);
    void deletePurchaseOrder(PurchaseOrder purchaseOrder);
    List<PurchaseOrder> getPurchaseOrdersByStatus(String status);
}
