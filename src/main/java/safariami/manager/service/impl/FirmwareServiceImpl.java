package safariami.manager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import safariami.manager.model.Firmware;
import safariami.manager.repo.FirmwareRepository;
import safariami.manager.service.FirmwareService;

@Service
public class FirmwareServiceImpl implements FirmwareService {
	
	@Autowired
	private FirmwareRepository firmwareRepository;

	@Override
	public List<Firmware> findAll() {
		return firmwareRepository.findAll();
	}

	@Override
	public Optional<Firmware> findById(Long id) {
		return firmwareRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		firmwareRepository.deleteById(id);
	}

	@Override
	public Firmware save(Firmware firmware) {
		return firmwareRepository.save(firmware);
	}

	@Override
	public Firmware findFirmwareByJobId(long id) {
		return firmwareRepository.findFirmwareByJobId(id);
	}

}
