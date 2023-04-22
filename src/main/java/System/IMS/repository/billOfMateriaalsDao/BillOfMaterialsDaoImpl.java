package System.IMS.repository.billOfMateriaalsDao;

import System.IMS.entity.BillOfMaterials;
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
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BillOfMaterialsDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public BillOfMaterials save(BillOfMaterials billOfMaterials) {
        String sql = "INSERT INTO bill_of_materials (name, quantity) VALUES (?, ?)";
        jdbcTemplate.update(sql, billOfMaterials.getName(), billOfMaterials.getQuantity());
        return billOfMaterials;
    }

    @Override
    public BillOfMaterials findById(Long id) {
        String sql = "SELECT * FROM bill_of_materials WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BillOfMaterialsMapper(), id);
    }

    @Override
    public List<BillOfMaterials> findAll() {
        String sql = "SELECT * FROM bill_of_materials";
        return jdbcTemplate.query(sql, new BillOfMaterialsMapper());
    }

    @Override
    public BillOfMaterials update(BillOfMaterials billOfMaterials) {
        String sql = "UPDATE bill_of_materials SET name = ?, quantity = ? WHERE id = ?";
        jdbcTemplate.update(sql, billOfMaterials.getName(), billOfMaterials.getQuantity(), billOfMaterials.getId());
        return billOfMaterials;
    }

    @Override
    public void delete(BillOfMaterials billOfMaterials) {
        String sql = "DELETE FROM bill_of_materials WHERE id = ?";
        jdbcTemplate.update(sql, billOfMaterials.getId());
    }

    private static class BillOfMaterialsMapper implements RowMapper<BillOfMaterials> {

        @Override
        public BillOfMaterials mapRow(ResultSet rs, int rowNum) throws SQLException {
            BillOfMaterials billOfMaterials = new BillOfMaterials();
            billOfMaterials.setId(rs.getLong("id"));
            billOfMaterials.setName(rs.getString("name"));
            billOfMaterials.setQuantity(rs.getInt("quantity"));
            return billOfMaterials;
        }
    }
}
