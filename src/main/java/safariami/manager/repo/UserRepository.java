package safariami.manager.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import safariami.manager.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUserName(String userName);
}
