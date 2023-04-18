package System.IMS.repository.activityLogDao;

import System.IMS.entity.ActivityLog;
import System.IMS.entity.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan("System.IMS.repository")
class ActivityLogDaoImplTest {

    @Autowired
    private ActivityLogDao activityLogDao;

    @PersistenceContext
    private EntityManager entityManager;

    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = new User("Test User");
        entityManager.persist(testUser);
    }

    @Test
    public void testSaveAndFindById() {
        ActivityLog activityLog = new ActivityLog(testUser, "Test Activity", LocalDateTime.now());
        activityLogDao.save(activityLog);

        Long id = activityLog.getId();
        assertNotNull(id);

        ActivityLog savedActivityLog = activityLogDao.findById(id);
        assertNotNull(savedActivityLog);
        assertEquals("Test Activity", savedActivityLog.getActivity());
    }

    @Test
    public void testFindAll() {
        ActivityLog activityLog1 = new ActivityLog(testUser, "Activity 1", LocalDateTime.now());
        ActivityLog activityLog2 = new ActivityLog(testUser, "Activity 2", LocalDateTime.now());
        entityManager.persist(activityLog1);
        entityManager.persist(activityLog2);

        List<ActivityLog> activityLogs = activityLogDao.findAll();
        assertNotNull(activityLogs);
        assertEquals(2, activityLogs.size());
    }

    @Test
    public void testFindByUser() {
        ActivityLog activityLog1 = new ActivityLog(testUser, "Activity 1", LocalDateTime.now());
        ActivityLog activityLog2 = new ActivityLog(testUser, "Activity 2", LocalDateTime.now());
        entityManager.persist(activityLog1);
        entityManager.persist(activityLog2);

        List<ActivityLog> activityLogs = activityLogDao.findByUser(testUser);
        assertNotNull(activityLogs);
        assertEquals(2, activityLogs.size());
    }

    @Test
    public void testDelete() {
        ActivityLog activityLog = new ActivityLog(testUser, "Test Activity", LocalDateTime.now());
        entityManager.persist(activityLog);

        activityLogDao.delete(activityLog);
        ActivityLog deletedActivityLog = entityManager.find(ActivityLog.class, activityLog.getId());
        assertNull(deletedActivityLog);
    }
}