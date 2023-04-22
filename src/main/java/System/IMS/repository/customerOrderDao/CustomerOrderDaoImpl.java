package System.IMS.repository.customerOrderDao;

import System.IMS.entity.CustomerOrder;
import System.IMS.entity.Inventory;
import System.IMS.entity.enams.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class CustomerOrderDaoImpl implements CustomerOrderDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerOrderDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(CustomerOrder customerOrder) {
        String sql = "INSERT INTO customer_order (item_id, quantity, unit_price, order_date, status) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, customerOrder.getInventory().getId(), customerOrder.getQuantity(), customerOrder.getUnitPrice(), customerOrder.getOrderDate(), customerOrder.getStatus().name());
    }

    @Override
    public void update(CustomerOrder customerOrder) {
        String sql = "UPDATE customer_order SET item_id = ?, quantity = ?, unit_price = ?, order_date = ?, status = ? WHERE id = ?";
        jdbcTemplate.update(sql, customerOrder.getInventory().getId(), customerOrder.getQuantity(), customerOrder.getUnitPrice(), customerOrder.getOrderDate(), customerOrder.getStatus().name(), customerOrder.getId());
    }

    @Override
    public void delete(CustomerOrder customerOrder) {
        String sql = "DELETE FROM customer_order WHERE id = ?";
        jdbcTemplate.update(sql, customerOrder.getId());
    }

    @Override
    public CustomerOrder findById(Long id) {
        String sql = "SELECT * FROM customer_order WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new CustomerOrderMapper(), id);
    }

    @Override
    public List<CustomerOrder> findAll() {
        String sql = "SELECT * FROM customer_order";
        return jdbcTemplate.query(sql, new CustomerOrderMapper());
    }

    private static class CustomerOrderMapper implements RowMapper<CustomerOrder> {

        @Override
        public CustomerOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
            CustomerOrder customerOrder = new CustomerOrder();
            customerOrder.setId(rs.getLong("id"));
            customerOrder.setOrderDate(rs.getDate("order_date").toLocalDate());
            customerOrder.setQuantity(rs.getInt("quantity"));
            customerOrder.setUnitPrice(rs.getBigDecimal("unit_price"));
            customerOrder.setStatus(OrderStatus.valueOf(rs.getString("status")));

            Inventory inventory = new Inventory();
            inventory.setId(rs.getLong("item_id"));
            customerOrder.setInventory(inventory);

            return customerOrder;
        }
    }
}