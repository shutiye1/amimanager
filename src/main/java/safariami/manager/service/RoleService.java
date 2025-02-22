package safariami.manager.service;

import java.util.List;
import java.util.Optional;
import safariami.manager.model.Role;

public interface RoleService {

    List<Role> findAll();
    Optional<Role> findById(Long id);
    Optional<Role> findByName(String Role);
    Role save(Role Role);
    void deleteById(Long id);
}
