package safariami.manager.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import safariami.manager.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{

	Optional<Role> findByName(String name);
}
