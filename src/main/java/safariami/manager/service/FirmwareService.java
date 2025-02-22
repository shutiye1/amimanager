package safariami.manager.service;

import java.util.List;
import java.util.Optional;

import safariami.manager.model.Firmware;

public interface FirmwareService {

    List<Firmware> findAll();
    Optional<Firmware> findById(Long id);
    void deleteById(Long id);
    Firmware save(Firmware firmware);
    Firmware findFirmwareByJobId(long id);
}
