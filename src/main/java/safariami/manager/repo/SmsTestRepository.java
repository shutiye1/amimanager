package safariami.manager.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import safariami.manager.model.SmsTest;

public interface SmsTestRepository extends JpaRepository<SmsTest, Long> {

}
