package System.IMS.repository.userDao;

import System.IMS.entity.User;

import java.util.List;

public interface UserDao {
    User findById(Long id);

    List<User> findAll();

    User save(User user);

    User update(User user);

    void delete(User user);
}
