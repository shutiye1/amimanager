package safariami.manager.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import safariami.manager.model.Firmware;

public interface FirmwareRepository extends JpaRepository<Firmware, Long>{

	Firmware findFirmwareByJobId(long id);
}
