package safariami.manager.service;

import java.util.List;
import java.util.Optional;

import safariami.manager.model.User;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    void deleteById(Long id);
    User save(User user);
    Optional<User> findByUserName(String userName);
}
