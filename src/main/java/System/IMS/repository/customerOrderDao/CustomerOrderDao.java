package System.IMS.repository.customerOrderDao;

import System.IMS.entity.CustomerOrder;

import java.util.List;

public interface CustomerOrderDao {
    void save(CustomerOrder customerOrder);
    void update(CustomerOrder customerOrder);
    void delete(CustomerOrder customerOrder);
    CustomerOrder findById(Long id);
    List<CustomerOrder> findAll();
}
